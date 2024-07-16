resource "aws_s3_bucket" "finapp_website" {
  bucket        = "tis-finapp-website"
  force_destroy = true
}

resource "aws_s3_bucket_website_configuration" "finapp_website_configuration" {
  bucket = aws_s3_bucket.finapp_website.id
  index_document {
    suffix = "index.html"
  }
  error_document {
    key = "error.html"
  }
}

resource "aws_s3_bucket_public_access_block" "public_access_block" {
  bucket                  = aws_s3_bucket.finapp_website.id
  block_public_acls       = false
  block_public_policy     = false
  ignore_public_acls      = false
  restrict_public_buckets = false
}

# resource "aws_s3_bucket_policy" "bucket_policy" {
#   bucket = aws_s3_bucket.finapp_website.id

#   policy = jsonencode({
#     Version = "2012-10-17"
#     Statement = [
#       {
#         Effect    = "Allow"
#         Principal = "*"
#         Action    = "s3:GetObject"
#         Resource  = "${aws_s3_bucket.finapp_website.arn}/*"
#       }
#     ]
#   })
# }

##### will upload all the files present under HTML folder to the S3 bucket #####
# resource "aws_s3_object" "upload_object" {
#   for_each      = fileset("html/", "*")
#   bucket        = aws_s3_bucket.bucket.id
#   key           = each.value
#   source        = "html/${each.value}"
#   etag          = filemd5("html/${each.value}")
#   content_type  = "text/html"
# }