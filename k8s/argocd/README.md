# ArgoCD
This part of the repo holds ArgoCD files for doing GitOps to run the platform components. Eventually we will add application components too.

## ArgoCD on OpenShift
### Bootstrap GitOps capabilities
Run the following command to bootstrap our cluster with OpenShift GitOps - it'll be used later to [declare, pull, and reconcile](https://opengitops.dev/) our system services (platform components) and app workloads (apps). Make sure to `oc login` as an admin first.

```until oc apply -k bootstrap/overlays/rhpds/; do sleep 15; done```

It's going to spit out a bunch of error looking output - which is OK, probably - our bootstrap isn't super clean and ordered so that's why we have it wrapped in `until` loop. It should eventually finish and tell you things are `created` and `configured`. An example bootstrap output [looks like this](./bootstrap-output.txt). Once it finishes, we still need to wait until ArgoCD installs all platform & application components defined in AppSets (:stopwatch: ~10 min in my testing).

### Folder Structure
The [./platform](platform) folder defines the YAML and kustomizations used for our components.

### Why ArgoCD for platform components?
These components are build by 3rd parties and just need to be installed and configured. They change rarely so there will be a bit of unnecessary overhead to do GitOps on them. So it almost seems counter intutive, maybe we should just kubctl apply or helm install and forget about it. However I do like the aspect that they **shouldn't** change so if they do we can have history in a PR. And if that change is unintentional/imperative, we have GitOps automatically put them back to the declared configuration/state (or warn us about it) - reliably consistent.