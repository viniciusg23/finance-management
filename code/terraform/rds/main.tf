resource "aws_db_instance" "finapp_database" {
  allocated_storage      = 10
  engine                 = "mysql"
  engine_version         = "8.0"
  instance_class         = "db.t3.micro"
  username               = var.db_username
  password               = var.db_password
  db_subnet_group_name   = data.terraform_remote_state.vpc.outputs.database_subnet_group_name
  vpc_security_group_ids = [aws_security_group.sg_rds.id]
  parameter_group_name   = "default.mysql8.0"
  skip_final_snapshot    = true
}