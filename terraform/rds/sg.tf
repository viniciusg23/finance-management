resource "aws_security_group" "sg_rds" {
  name        = "rds-finapp-sg"
  description = "Allow all traffic from private subnet to database subnet"
  vpc_id      = data.terraform_remote_state.vpc.outputs.vpc_id


  ingress {
    description     = "Allow trafic from the private suibnet to the database"
    from_port       = "3306"
    to_port         = "3306"
    protocol        = "tcp"
    security_groups = data.aws_security_groups.ecs_sg.ids
  }

}