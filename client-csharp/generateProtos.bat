setlocal

@rem enter this directory
cd /d %~dp0

set TOOLS_PATH=packages\Grpc.Tools.1.13.1\tools\windows_x64

%TOOLS_PATH%\protoc.exe -I ..\proto chat.proto --csharp_out . 
%TOOLS_PATH%\protoc.exe -I ..\proto chat.proto --grpc_out . --plugin=protoc-gen-grpc=%TOOLS_PATH%\grpc_csharp_plugin.exe

endlocal