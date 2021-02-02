import os

import markdown
from bs4 import BeautifulSoup


def build_readme(src: str, dest: str):
    if not os.path.exists(dest):
        os.makedirs(dest)
    with open(src, 'r') as source:
        html = markdown.markdown(source.read())
    soup = BeautifulSoup(html, 'html.parser')
    # Reconstruct title
    title = soup.find('h1')
    title.find_next_sibling('p').decompose()
    new_title = soup.new_tag('p')
    new_title.string = 'Dracula Theme for JetBrains'
    title.replace_with(new_title)
    new_title.insert_after(soup.new_tag('br'))
    # Remove blockquote
    blockquote_h2 = soup.find('blockquote')
    blockquote_h2.decompose()
    # Set image widths
    img = soup.find('img', alt='Screenshot')
    img['src'] = 'https://raw.githubusercontent.com/dracula/jetbrains/master/screenshot.png'
    img['width'] = '600'
    # Remove team
    team_h2 = soup.find('h2', text="Team")
    for p in team_h2.find_all_next('p', limit=2):
        p.decompose()
    team_h2.decompose()
    # Replace Dracula Pro screenshot URL
    dracula_pro_h2 = soup.find('h2', text="Dracula PRO")
    dracula_pro_img_link = dracula_pro_h2.find_next('p').find('a').find('img')
    dracula_pro_img_link['src'] = \
        'https://raw.githubusercontent.com/dracula/jetbrains/master/docs/screenshots/dracula-pro.png'
    # Replace Contribution Guide URL
    contribution_h2 = soup.find('h2', text="Contribution")
    contribution_guide_link = contribution_h2.find_next('p').find('a')
    contribution_guide_link['href'] = 'https://github.com/dracula/jetbrains/blob/master/CONTRIBUTING.md'
    # Remove license
    license_h2 = soup.find('h2', text='License')
    license_h2.find_next('p').decompose()
    license_h2.decompose()
    with open(os.path.join(dest, 'README.html'), 'w') as output_file:
        output_file.write(str(soup).strip())
