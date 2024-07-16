terraform {
  required_version = ">= 1.0"
  backend "s3" {
    bucket = "tis-finapp-terraform-state"
    region = "us-east-1"
    key    = "rds/terraform.state"
  }
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "5.47.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}
