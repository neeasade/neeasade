#!/bin/sh

state=$(cat readme_pre.txt)
links=$(cat links.txt | shuf)

for link in $links; do
    echo "$link"
    state=$(echo "$state" | sed "s#,#[[${link}][🌳]]#")
done

echo "$state" >readme.org
