import os
import markdown
from bs4 import BeautifulSoup

SOURCE_FILE = os.path.join(os.path.dirname(__file__), '..', 'README.md')
DEST_PATH = os.path.join(os.path.dirname(__file__), '..', 'build')

if __name__ == '__main__':
    if not os.path.exists(DEST_PATH):
        os.makedirs(DEST_PATH)

    with open(SOURCE_FILE, 'r') as source:
        html = markdown.markdown(source.read())
        soup = BeautifulSoup(html, 'html.parser')

        # Remove title
        soup.find('h1').decompose()
        # Remove build badges
        soup.find('p').decompose()

        # Set image widths
        for img in soup.find_all('img'):
            img['width'] = '700'

        # Add margin above images
        for img in soup.find_all('img'):
            img.insert_before(soup.new_tag('br'))

        # remove installation
        installation = soup.find('h2', text='Installation')
        installation.find_next('ol').decompose()
        installation.decompose()

    with open(os.path.join(DEST_PATH, 'README.html'), 'w') as output_file:
        output_file.write(str(soup).strip())
