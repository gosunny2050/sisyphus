rootProject.name = "sisyphus"

include("lib:sisyphus-common")
include("lib:sisyphus-dsl")
include("lib:sisyphus-dto")
include("lib:sisyphus-jackson")
include("lib:sisyphus-jackson-protobuf")
include("lib:sisyphus-gson-protobuf")
include("lib:sisyphus-protobuf")
include("lib:sisyphus-grpc")
include("lib:sisyphus-grpc-coroutine")
include("lib:sisyphus-grpc-rxjava")
include("lib:sisyphus-test")

include("tools:sisyphus-protoc")
include("tools:sisyphus-project-gradle-plugin")
include("tools:sisyphus-protobuf-gradle-plugin")
include("tools:sisyphus-api-linter-runner")
include("tools:sisyphus-protoc-runner")

include("middleware:sisyphus-configuration-artifact")
include("middleware:sisyphus-jdbc")
include("middleware:sisyphus-mongo")
include("middleware:sisyphus-redis")
include("middleware:sisyphus-amqp")
include("middleware:sisyphus-rocketmq")
include("middleware:sisyphus-elastic")
include("middleware:sisyphus-hbase")
include("middleware:sisyphus-grpc-client")
include("middleware:sisyphus-retrofit")
include("middleware:sisyphus-grpc-client-kubernetes")

include("starter:sisyphus-jackson-starter")
include("starter:sisyphus-webflux-starter")
include("starter:sisyphus-grpc-server-starter")
include("starter:sisyphus-grpc-transcoding-starter")
include("starter:sisyphus-protobuf-type-server-starter")
include("starter:sisyphus-spring-boot-test-starter")

include("dependencies")
project(":dependencies").name = "sisyphus-dependencies"
include("bom")
project(":bom").name = "sisyphus-bom"