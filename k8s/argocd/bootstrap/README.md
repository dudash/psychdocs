# Bootstrap
This is where we define Kustomize-able resources that will bootstrap the cluster with minimal required capabilities.

Bootstap has a 'base' and uses 'overlays' that modify the common base.
An 'overlay' is just another kustomization, referring to the 'base', and referring to patches to apply to that base.

## Current Base List
* OpenShift GitOps Operator

## Overlays
Most overlays use all the 'components'.
The only overlay implemented right now is:
* RHPDS

## Components
* ArgoCD AppProject
* ArgoCD AppSets
