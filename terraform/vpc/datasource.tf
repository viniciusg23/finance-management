data "aws_security_groups" "ecs_sg" {
  filter {
    name   = "tag:Name"
    values = ["finapp-backend"]
  }
}

