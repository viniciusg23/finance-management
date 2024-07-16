resource "aws_lb" "alb" {
  name               = "tis-finapp"
  load_balancer_type = "application"
  subnets            = data.terraform_remote_state.vpc.outputs.public_subnets
  security_groups    = [aws_security_group.http.id]
}

resource "aws_lb_target_group" "app" {
  name_prefix = "app-"
  vpc_id      = data.terraform_remote_state.vpc.outputs.vpc_id
  protocol    = "HTTP"
  port        = 8080
  target_type = "ip"

  health_check {
    enabled             = true
    path                = "/healthcheck"
    port                = 8080
    matcher             = 200
    interval            = 40
    timeout             = 30
    healthy_threshold   = 2
    unhealthy_threshold = 3
  }
}

resource "aws_lb_listener" "http" {
  load_balancer_arn = aws_lb.alb.id
  port              = 80
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.app.id
  }
}

resource "aws_lb_listener" "https" {
  load_balancer_arn = aws_lb.alb.id
  port              = 443
  protocol          = "HTTPS"
  certificate_arn   = data.terraform_remote_state.acm.outputs.finapp_certificate_arn

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.app.id
  }
}