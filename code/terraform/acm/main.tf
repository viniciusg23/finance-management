resource "aws_acm_certificate" "finapp_certificate" {
  domain_name               = "finapp.cloud"
  subject_alternative_names = ["*.finapp.cloud"]
  validation_method         = "DNS"

  tags = {
    Name = "finapp.cloud SSL certificate"
  }
}