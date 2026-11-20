#!/bin/sh
MAVEN_VERSION="3.9.9"
MAVEN_DIST="apache-maven-$MAVEN_VERSION"
MAVEN_HOME="$HOME/.m2/wrapper/dists/$MAVEN_DIST"
MAVEN_ZIP="$HOME/.m2/wrapper/dists/$MAVEN_DIST-bin.zip"
MAVEN_URL="https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/$MAVEN_VERSION/$MAVEN_DIST-bin.zip"

if [ ! -x "$MAVEN_HOME/bin/mvn" ]; then
  echo "Maven $MAVEN_VERSION no esta instalado. Descargando..."
  mkdir -p "$HOME/.m2/wrapper/dists"

  if command -v curl >/dev/null 2>&1; then
    curl -fL "$MAVEN_URL" -o "$MAVEN_ZIP" || exit 1
  elif command -v wget >/dev/null 2>&1; then
    wget "$MAVEN_URL" -O "$MAVEN_ZIP" || exit 1
  else
    echo "ERROR: Se necesita curl o wget para descargar Maven."
    exit 1
  fi

  unzip -q -o "$MAVEN_ZIP" -d "$HOME/.m2/wrapper/dists" || exit 1
  rm -f "$MAVEN_ZIP"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
