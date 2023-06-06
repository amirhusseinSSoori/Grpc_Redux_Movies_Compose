# GRPC(Redux_Movies_Compose)
Sometimes developers want to be different from other guys. This repository was created some years ago when I worked at the University of Sharif Now find time and update this and try to maintain the code when will find time. Yeah, Grpc, I remember when I heard this protocol, I hadn't worked with RestApi very well But Grpc is so much easier than Rest API in Android. Now I want to describe this repository with So many Technology like redux and Jetpack Compose and other things.

So first clone this repository for run in client Python Server  : https://github.com/M-CAP7AIN/gRPC-Python-Server and set your Ip in BaseIp in (Constance class in client).Before that, you should read some articles about Grpc technology.

```kotlin
object Constance {
    const val BaseIp = ""
    const val BasePort = 50051
    const val NoError ="NoError"
}
```
And There are  Three Grpc Services (Unray) for Communication with the server.

1 - get list Movies with filter ( GetVideosX ).

2 - Search ( SearchVideosX ).

3 - banner ( GetHeadersX ).


```kotlin
service Body {
  rpc GetVideosX (VideoListXRequest) returns (VideoListXReply) {}
  rpc SearchVideosX (VideoListXRequest) returns (VideoListXReply) {}
  rpc GetHeadersX (VideoHeaderXRequest) returns (VideoHeaderXReply) {}
}

message VideoListXRequest {
  string filter = 1;
}

message VideoListXReply {
  repeated VideoListXModel VideoListX = 1;
}

message VideoListXModel {
  int32  ID = 1;
  string Name = 2;
  string Description = 3;
  string Picture = 4;
  string Category = 5;
  int32  Views = 6;
  int32  Year = 7;
  string Director = 8 ;
  string Cast = 9;
}

message VideoHeaderXRequest {
  string filter = 1;
}

message VideoHeaderXReply {
  repeated VideoHeaderXModel VideoHeaderX = 1;
}

message VideoHeaderXModel {
  int32  ID = 1;
  int32  ID_VIDEO = 2;
  string Name = 3;
  string Description = 4;
  string Picture = 5;
}
```










 
