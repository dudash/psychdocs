# The webapp
Simple UI to allow for using the psychdocs.

Also, an admin UI for data ingest, finetuning controls.

## Developer's Notes
Part of the tool and framework selection was based on tools newer than `create-react-app`. This app mostly runs **client-side** in the user's webbrowser. It leverages React + Typescript + Vite (you can read [the details of that here](./README-react-ts-vite.md)). It also is using [MUI-joy](https://mui.com/joy-ui/getting-started/) for styling. Here's a list of links to components, frameworks, and tools.
* React
* Vite
* Remix.run
* Typescript
* We also leverage MUI for a nice consistent look and feel

### About APIs
Trying to keep the microservices documented nicely with API specs. details TBD.

### Running Locally
You will need [pnpm](https://pnpm.io/installation) and node.js
It's as easy as:
* `pnpm install`
* `pnpm dev`

### Deploying to a k8s cluster
TBD - working on getting a [helm chart](../k8s/charts/webapp/) going for this

### Deploying to other locations (like CloudFlare)
TBD
