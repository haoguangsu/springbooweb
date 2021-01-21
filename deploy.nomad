job "minioservice" {
  datacenters = [
    "dc1"]

  type = "service"

  group "app" {
    count = 1

    task "minioservice" {
      driver = "docker"

      artifact {
        source = "http://10.16.196.85:2999/minioservice.tar"
      }

      config {
        load = "minioservice.tar"
        image = "minioservice"
        network_mode = "host"
        port_map {
          http = 18003
        }
      }

      resources {
        memory = 2048

        network {
          port "http" {
            static = 18003
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
