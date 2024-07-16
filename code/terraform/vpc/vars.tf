variable "region" {
  type = string
}

variable "name" {
  type = string
}

variable "azs" {
  type = list(any)
}

variable "cidr" {
  type = string
}

variable "private_subnets" {
  type = list(any)
}

variable "database_subnets" {
  type = list(any)
}

variable "public_subnets" {
  type = list(any)
}

locals {
  tags = {
    Team                               = "finapp"
    Environment                        = "dev"
    "kubernetes.io/cluster/production" = "shared"
    Proposal                           = "finapp-shared-vpc"
    Kind                               = "vpc"
  }

  public_subnet_tags = {
    "kubernetes.io/role/elb"           = "1"
    "kubernetes.io/cluster/production" = "shared"

  }

  private_subnet_tags = {
    "kubernetes.io/role/internal-elb"   = "1"
    "kubernetes.io/cluster/production"  = "shared"
    "karpenter.sh/discovery/production" = "private"
    Name                                = "vpc-private"
  }
}