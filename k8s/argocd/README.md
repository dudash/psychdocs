# ArgoCD
This part of the repo holds ArgoCD files for doing GitOps to run the platform components. Eventually we will add application components too.

## ArgoCD on OpenShift
### Bootstrap GitOps capabilities
Run the following command to bootstrap our cluster with OpenShift GitOps - it'll be used later to [declare, pull, and reconcile](https://opengitops.dev/) our system services (platform components) and app workloads (apps). Make sure to `oc login` as an admin first.

```until oc apply -k bootstrap/overlays/rhpds/; do sleep 15; done```

It's going to spit out a bunch of error looking output - which is OK, probably - our bootstrap isn't super clean and ordered so that's why we have it wrapped in `until` loop. It should eventually finish and tell you things are `created` and `configured`. An example bootstrap output [looks like this](./.docs/bootstrap-output.txt). Once it finishes, we still need to wait until ArgoCD installs all platform & application components defined in AppSets (:stopwatch: ~10 min in my testing).
