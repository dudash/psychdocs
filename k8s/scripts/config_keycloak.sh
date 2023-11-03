#!/bin/bash
# set variable for the namespace
NAMESPACE=psychdocs
SSO_NAMESPACE=$NAMESPACE

echo "Waiting for keycloak to be ready..."
until kubectl get pod keycloak-0 -n $SSO_NAMESPACE -o jsonpath='{.status.containerStatuses[0].ready}' | grep true &> /dev/null; do echo -n "."; sleep 0.5; done

echo "Keycloak credentials:"
SSO_USER=kubectl get secret credential-psychdocs-keycloak -n $SSO_NAMESPACE -o jsonpath='{.data.ADMIN_USERNAME}' | base64 --decode; echo
SSO_PASS=kubectl get secret credential-psychdocs-keycloak -n $SSO_NAMESPACE -o jsonpath='{.data.ADMIN_PASSWORD}' | base64 --decode; echo
echo $SSO_USER
echo $SSO_PASS

SSO_ACCOUNT_URI=https://$(kubectl get route keycloak -n $SSO_NAMESPACE -o jsonpath='{.spec.host}')/auth/realms/basic/account
echo "Keycloak account URL:"
echo $SSO_ACCOUNT_URI

SSO_GITHUB_REDIRECT_URI=https://$(kubectl get route keycloak -n $SSO_NAMESPACE -o jsonpath='{.spec.host}')/auth/realms/basic/broker/github/endpoint
echo "Keycloak callback URL:"
echo $SSO_GITHUB_REDIRECT_URI
echo "You need to create an OAuth app in GitHub AND put those setting in the github identity provider in Keycloak to federate login"
echo "maybe we can automate that in the future ¯\_(ツ)_/¯"