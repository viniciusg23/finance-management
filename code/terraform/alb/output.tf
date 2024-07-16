output "alb_arn" {
  value = aws_lb.alb.arn
}

output "alb_dns_name" {
  value = aws_lb.alb.dns_name
}

output "alb_target_group_arn" {
  value = aws_lb_target_group.app.arn
}

output "alb_sgr_id" {
  value = aws_security_group.http.id
}