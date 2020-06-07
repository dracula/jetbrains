import os

from changelog import build_changelog
from readme import build_readme

if __name__ == '__main__':
    readme_src = os.path.join(os.path.dirname(__file__), '..', 'README.md')
    readme_dest = os.path.join(os.path.dirname(__file__), '..', 'build')
    build_readme(readme_src, readme_dest)
    changelog_src = os.path.join(os.path.dirname(__file__), '..', 'CHANGELOG.md')
    changelog_dest = os.path.join(os.path.dirname(__file__), '..', 'build')
    build_changelog(changelog_src, changelog_dest)
