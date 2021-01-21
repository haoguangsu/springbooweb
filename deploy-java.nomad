job "minioservice" {
  datacenters = [
    "dc1"]

  type = "service"

  group "app" {
    count = 1

    task "minioservice" {
      driver = "java"

      artifact {
        source = "http://shr.sp5000.repo:2999/MinioService-1.0.2.RELEASE.jar"
      }

      config {
        jar_path = "local/MinioService-1.0.2.RELEASE.jar"
        jvm_options = ["-Xmx6144m","-Xms2048m"]
        args = []
      }

      resources {
        memory = 6000

        network {
          port "http" {
            static = 18002
          }
        }
      }

      service {
        name = "minioservice"
        port = "http"

        check {
          name = "alive"
          type = "tcp"
          interval = "10s"
          timeout = "2s"
        }
        tags = [
          "{\"gateway\":\"/inspect\",\"swagger\":\"/v3/api-docs\"}"]
      }
    }
  }
}
