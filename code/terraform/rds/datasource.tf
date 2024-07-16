data "terraform_remote_state" "vpc" {
  backend   = "s3"
  workspace = terraform.workspace

  config = {
    bucket  = "tis-finapp-terraform-state"
    region  = "us-east-1"
    key     = "vpc/terraform.state"
    profile = "terraform"
  }
}

data "aws_security_groups" "ecs_sg" {
  filter {
    name   = "tag:Name"
    values = ["finapp-backend"]
  }
}