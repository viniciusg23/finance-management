resource "aws_security_group" "http" {
  name        = "http-finapp-sg"
  description = "Allow all HTTP/HTTPS traffic from public"
  vpc_id      = data.terraform_remote_state.vpc.outputs.vpc_id

  dynamic "ingress" {
    for_each = [80, 443]
    content {
      protocol    = "tcp"
      from_port   = ingress.value
      to_port     = ingress.value
      cidr_blocks = ["0.0.0.0/0"]
    }
  }

  egress {
    protocol    = "-1"
    from_port   = 0
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}