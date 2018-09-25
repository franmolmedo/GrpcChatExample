using System;
using System.Threading;
using System.Windows;
using Grpc.Core;
using Service;

namespace ChatClient
{
    public partial class MainWindow
    {
        private ChatService.ChatServiceClient _chatServiceClient;
        private AsyncDuplexStreamingCall<ChatMessage, ChatMessage> _call;

        public MainWindow()
        {
            InitializeComponent();

            InitializeGrpc();        
        }

        private async void InitializeGrpc()
        {
            var grpcChannel = new Channel("127.0.0.1:8080", ChannelCredentials.Insecure);

            _chatServiceClient = new ChatService.ChatServiceClient(grpcChannel);

            try
            {
                using (_call = _chatServiceClient.chat())
                {
                    while (await _call.ResponseStream.MoveNext(CancellationToken.None))
                    {
                        var serverMessage = _call.ResponseStream.Current;
                        var displayMessage =
                            $"{serverMessage.From}: {serverMessage.Message}{Environment.NewLine}";
                        ChatContent.Text += displayMessage;
                    }
                }
            }
            catch (RpcException)
            {
                _call = null;
                throw;
            }
        }

        private async void ButtonSend_OnClick(object sender, RoutedEventArgs e)
        {
            if (_chatServiceClient == null) return;
   
            await _call.RequestStream.WriteAsync(new ChatMessage
            {
                From = Name.Text,
                Message = Message.Text
            });

            Message.Text = string.Empty;
        }
    }
}
