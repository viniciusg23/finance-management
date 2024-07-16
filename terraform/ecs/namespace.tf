resource "aws_service_discovery_http_namespace" "this" {
  name        = "finapp"
  description = "CloudMap namespace for ecs"
  tags = {
    Environment = terraform.workspace
    Project     = "finapp"
  }
}
