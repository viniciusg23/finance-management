module "ecs" {
  source  = "terraform-aws-modules/ecs/aws"
  version = "~> 5.0"

  cluster_name = "finapp-cluster"

  cluster_configuration = {
    execute_command_configuration = {
      logging = "OVERRIDE"
      log_configuration = {
        cloud_watch_log_group_name = "/aws/ecs/aws-ec2"
      }
    }
  }

  fargate_capacity_providers = {
    FARGATE = {
      default_capacity_provider_strategy = {
        weight = 50
      }
    }
    FARGATE_SPOT = {
      default_capacity_provider_strategy = {
        weight = 50
      }
    }
  }

  services = {
    finapp-backend = {
      cpu    = 512
      memory = 1024

      # Container definition(s)
      container_definitions = {
        app-finapp = {
          cpu       = 256
          memory    = 512
          essential = true
          image     = "891377060486.dkr.ecr.us-east-1.amazonaws.com/finapp-backend-repository:03af46cb7997100e9d9c8bfc9ed6502dfda904ff"
          port_mappings = [
            {
              name          = "app-finapp"
              containerPort = 8080
              protocol      = "tcp"
            }
          ]

          # Example image used requires access to write to root filesystem
          readonly_root_filesystem  = false
          enable_cloudwatch_logging = false
          memory_reservation        = 100
        }
      }

      service_connect_configuration = {
        namespace = "finapp"
        service = {
          client_alias = {
            port     = 8080
            dns_name = "app-finapp"
          }
          port_name      = "app-finapp"
          discovery_name = "app-finapp"
        }
      }

      load_balancer = {
        service = {
          target_group_arn = data.terraform_remote_state.alb.outputs.alb_target_group_arn
          container_name   = "app-finapp"
          container_port   = 8080
        }
      }

      subnet_ids = data.terraform_remote_state.vpc.outputs.private_subnets
      security_group_rules = {
        alb_ingress_3000 = {
          type                     = "ingress"
          from_port                = 8080
          to_port                  = 8080
          protocol                 = "tcp"
          description              = "Service port"
          source_security_group_id = data.terraform_remote_state.alb.outputs.alb_sgr_id
        }
        egress_all = {
          type        = "egress"
          from_port   = 0
          to_port     = 0
          protocol    = "-1"
          cidr_blocks = ["0.0.0.0/0"]
        }
      }
    }
  }

  tags = {
    Environment = terraform.workspace
    Project     = "tis-finapp"
  }
}