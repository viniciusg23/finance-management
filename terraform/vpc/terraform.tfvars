name             = "vpc"
region           = "us-east-1"
cidr             = "10.1.0.0/16"
azs              = ["us-east-1a", "us-east-1b"]
public_subnets   = ["10.1.0.0/19", "10.1.32.0/19"]
private_subnets  = ["10.1.64.0/18", "10.1.128.0/18"]
database_subnets = ["10.1.192.0/19", "10.1.224.0/19"]