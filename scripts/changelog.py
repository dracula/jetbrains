import os

import markdown


def build_changelog(src: str, dest: str):
    if not os.path.exists(dest):
        os.makedirs(dest)
    with open(src, 'r') as source:
        html = markdown.markdown(source.read())
        html = html.replace('<h1>Changelog</h1>\n', '')
    with open(os.path.join(dest, 'CHANGELOG.html'), 'w') as output_file:
        output_file.write(html)
