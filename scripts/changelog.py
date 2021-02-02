import os

import markdown
from bs4 import BeautifulSoup


def build_changelog(src: str, dest: str):
    if not os.path.exists(dest):
        os.makedirs(dest)
    with open(src, 'r') as source:
        html = markdown.markdown(source.read())
    soup = BeautifulSoup(html, 'html.parser')
    soup.find('h1').decompose()
    with open(os.path.join(dest, 'CHANGELOG.html'), 'w') as output_file:
        output_file.write(str(soup).strip())
