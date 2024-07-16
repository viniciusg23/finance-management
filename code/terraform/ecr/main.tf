resource "aws_ecr_repository" "finapp_backend_repository" {
  name                 = "finapp-backend-repository"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}