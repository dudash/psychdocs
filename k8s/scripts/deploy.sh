#!/bin/bash

# make sure I'm logged into kubernetes
if ! kubectl get nodes &> /dev/null; then
  echo "You must be logged into Kubernetes/OpenShift to run this script"
  exit 1
fi

echo "You are logged in as: $(kubectl config view --minify -o jsonpath='{.contexts[0].context.user}')"
echo "You are currently in namespace: $(kubectl config view --minify -o jsonpath='{.contexts[0].context.namespace}')"
kubectl version
echo "Cluster ID:" $(kubectl get clusterversion -o jsonpath='{.items[].spec.clusterID}')

# create a list of options for overlay to use from the folder ../arcgocd/bootstrap/overlays, allow the user to select one
echo "Select the details of K8s/OpenShift cluster you have:"
select overlay in $(ls -1 ../argocd/bootstrap/overlays); do
  if [ -n "$overlay" ]; then
    echo "You selected: $overlay"
    until kubectl apply -k ../argocd/bootstrap/overlays/$overlay/; do sleep 15; done
    break
  fi
  echo "You must select a valid number from the list"
done

