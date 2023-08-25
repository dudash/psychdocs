# doc QA CLI tool
Query the docs with this CLI tool.

install dependencies:
> ```poetry install```

build the vector database first:
> ```poetry run python gen_db_faiss.py```

Then you can run queries:
> ```poetry run python main.py "How much is stress is too much"```