# psychdocs (DRAFT)
Using LLMs to access psychology docs via natural language. Technically, this is known as [Retreival Augemented Generation](https://www.promptingguide.ai/techniques/rag).

The plan for this system architecture is to build out several components that can deploy to, scale within, and be managed using Kubernetes/OpenShift:
* [webapp](./webapp)
* query_api
* [ingester](./ingester)
* finetuner
* [docqa_cli](./docqa_cli)

Right now the CLI works but the rest is still in work.


## arch sketch
I've been iterating on the architecure design, [the DRAFT](https://excalidraw.com/#json=bc_tmEdGVW69KL_LR1VAC,TJTXQuB5d-ckQKvW4UNBGA) fits together like this:
<img src="./arch.png"/>

Some early flow designs:
<img src="./services_flow.png"/>

Initial data design thoughts:
<img src="./data_design.png"/>
