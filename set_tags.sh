BRANCH="master"

if [ "$TRAVIS_BRANCH" = "$BRANCH" ]; then
  if [ "$TRAVIS_PULL_REQUEST" = false ]; then
    if [ -z "$TRAVIS_TAG" ]; then
      git config --global user.email "travis@travis-ci.org"
      git config --global user.name "Travis"

      git tag -a $(date +"%d.%m.%y %T") -m "Travis build at $(date +"%d.%m.%y %T")"
      git push origin --tags
      git fetch origin
    fi
  fi
fi
