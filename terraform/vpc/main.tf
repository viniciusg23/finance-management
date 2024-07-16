module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 5.0"

  name                               = var.name
  cidr                               = var.cidr
  azs                                = var.azs
  private_subnets                    = var.private_subnets
  public_subnets                     = var.public_subnets
  database_subnets                   = var.database_subnets
  create_database_subnet_route_table = true
  tags                               = local.tags
  public_subnet_tags                 = local.public_subnet_tags
  private_subnet_tags                = local.private_subnet_tags
  enable_nat_gateway                 = false
  enable_vpn_gateway                 = false
}


# resource "aws_vpc_endpoint" "ecr_dkr" {
#   vpc_id              = module.vpc.vpc_id
#   service_name        = "com.amazonaws.us-east-1.ecr.dkr"
#   vpc_endpoint_type   = "Interface"
#   private_dns_enabled = true

#   security_group_ids = [aws_security_group.sg_vpc_endpoints.id]
#   subnet_ids         = module.vpc.private_subnets

# }

# resource "aws_vpc_endpoint" "ecr_api" {
#   vpc_id              = module.vpc.vpc_id
#   service_name        = "com.amazonaws.us-east-1.ecr.api"
#   vpc_endpoint_type   = "Interface"
#   private_dns_enabled = true

#   security_group_ids = [aws_security_group.sg_vpc_endpoints.id]
#   subnet_ids         = module.vpc.private_subnets

# }

# resource "aws_vpc_endpoint" "s3" {
#   vpc_id            = module.vpc.vpc_id
#   service_name      = "com.amazonaws.us-east-1.s3"
#   vpc_endpoint_type = "Gateway"

#   route_table_ids = module.vpc.private_route_table_ids
# }