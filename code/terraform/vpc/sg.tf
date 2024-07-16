resource "aws_security_group" "sg_vpc_endpoints" {
  name        = "vpc-endpoints-finapp-sg"
  description = "Associated to ECR/s3 VPC Endpoints"
  vpc_id      = module.vpc.vpc_id

  ingress {
    description     = "Allow Nodes to pull images from ECR via VPC endpoints"
    protocol        = "tcp"
    from_port       = 443
    to_port         = 443
    security_groups = data.aws_security_groups.ecs_sg.ids
  }
}