# Originally from: https://gist.github.com/kennethleungty/7e62a175633a951affa8ca40d62a4320#file-main-py
# Original author: Kenneth Leung
# Snapshot date: 2023-08-01

import argparse
import timeit
from qa_utils import setup_dbqa

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument('input', type=str)
    args = parser.parse_args()

    start = timeit.default_timer()
    dbqa = setup_dbqa()
    response = dbqa({'query': args.input}) # Parse input from argparse into QA object
    end = timeit.default_timer()

    # Print document QA response
    print(f'\nAnswer: {response["result"]}')
    print('='*50) # Formatting separator

    # Process source documents for better display
    source_docs = response['source_documents']
    for i, doc in enumerate(source_docs):
        print(f'\nSource Document {i+1}\n')
        print(f'Source Text: {doc.page_content}')
        print(f'Document Name: {doc.metadata["source"]}')
        print(f'Page Number: {doc.metadata["page"]}\n')
        print('='* 50) # Formatting separator
        
    # Display time taken for CPU inference
    print(f"Time to retrieve response: {end - start}")