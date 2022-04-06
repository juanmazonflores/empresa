job('job-DSL-1'){
  description('Job para nuestra empresa')
  scm{
    git('https://github.com/juanmazonflo/empresa.git','main') { node ->
      node / gitConfigName('juanmazonflo')
      node / gitConfigEmail('juanitocapotillo@gmail.com')
    }
  }
  parameters{
    stringParam('usuario',defaultValue='Juan',description='Nombre del usuario')
    choiceParam('artista',['Artista 1','Artista 2','Artista 3','Artista 4','Artista 5'])
    choiceParam('genero',['Rock','Pop','Rap','Jazz','Blues','Country','Folk'])
    booleanParam('auto_reproducir',false)
  }
  triggers{
    cron('H/7 * * * *')
    githubPush()
  }
  steps{
    shell("bash jobscript.sh")  
  }
  publishers{
    mailer('juanmazonpracticasempresa@gmail.com',true,true)
    slackNotifier{
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
