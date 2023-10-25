# Overlay to RHPDS
Deploy to a Red Hat demo platform. We have 3 resources here:

1. Deploy ArgoCD as an instance managed by the Red Hat GitOps operator
2. Create a cluster admin user (needed in an RHPDS cluster to share auth with ArgoCD)
3. Setup RBAC for cluster admin user (needed in an RHPDS cluster to share auth with ArgoCD)