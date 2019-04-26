import os
import markdown

SOURCE_FILE = os.path.join(os.path.dirname(__file__), '..', 'CHANGELOG.md')
DEST_PATH = os.path.join(os.path.dirname(__file__), '..', 'build')

if __name__ == '__main__':
  if not os.path.exists(DEST_PATH):
    os.makedirs(DEST_PATH)
  with open(SOURCE_FILE, 'r') as source:
    html = markdown.markdown(source.read())
    html = html.replace('<h1>Changelog</h1>\n', '')
  with open(os.path.join(DEST_PATH, 'CHANGELOG.html'), 'w') as output_file:
    output_file.write(html)
