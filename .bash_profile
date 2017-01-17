export PATH="/usr/local/sbin:$PATH"
source $(brew --prefix php-version)/php-version.sh && php-version 7
export PATH="/usr/bin:$PATH"
export PATH="/usr/local/lib/node_modules/cordova/bin/:$PATH"
export PATH="~/phantomjs-2.1.1/bin/:$PATH"

export NVM_DIR="$HOME/.nvm"
. "$(brew --prefix nvm)/nvm.sh"

export PATH="$HOME/.composer/vendor/bin:$PATH"
# BEGIN -- git branch parsing and autocompletion.
source /usr/local/etc/bash_completion.d/git-completion.bash

parse_git_branch() {
  GITSTT=$(git status)
  MSGSTT=""


  if [[ $GITSTT == *"Untracked"* ]] || [[ $GITSTT == *"Changes not staged for commit"* ]]
  then
      MSGSTT="\033[31m?\033[0m"
  else
    if [[ $GITSTT == *"Changes to be committed"* ]]
    then
      MSGSTT="\033[93m?\033[0m"
    else
      MSGSTT="\033[92m!\033[0m"
    fi
  fi

  GITBRH=$(git branch | sed -e '/^[^*]/d' -e 's/* \(.*\)/\1/')
  echo -e "("$GITBRH$MSGSTT")"

  #git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
# END -- git branch parsing and autocompletion.
export PS1="\u [\w]\$(parse_git_branch) $ "

export PATH="~/Library/Android/sdk/tools/:$PATH"
export PATH="/usr/local/bin/:$PATH"
export PATH="/Applications/Jenkins/:$PATH"


get_color_directory() {
  git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}

export CLICOLOR=1
export LSCOLORS=ExFxBxDxCxegedabagacad
alias ls='ls -GFh'


export PATH="~/Projects/scripts/:$PATH"
export NODENV_ROOT=/usr/local/var/nodenv

if which nodenv > /dev/null; then eval "$(nodenv init -)"; fi

export PATH="~/.npm-packages/bin:$PATH"
