<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="CareWatch">
    <link rel="icon" href="images/favicon.ico">

    <title>CareWatch</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="bootstrap/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="bootstrap/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Custom styles for this template -->
    <link href="css/carousel.css" rel="stylesheet">
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a href="#"><img class="first-slide" src="images/logoCW60x60.jpg" alt="Logo de CareWatch"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
                <li ><a href="#">Accueil</a></li>
                <!--<li><a href="#about">À propos</a></li>
                <li><a href="#contact">Contact</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>-->
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>


    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img class="first-slide" src="images/logoCW500x500.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <!--<h1 class="welcome">Bienvenue</h1>-->
              <p></p>
              <p><a class="btn btn-lg btn-primary" href="#" role="button">Souscrire maintenant</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="images/forest-500x500.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>Système Anti-fugue</h1>
              <!--<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>-->
              <p><a class="btn btn-lg btn-primary" href="#Fugue" role="button">En savoir plus</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="third-slide" src="images/ulk5OY.jpg" alt="Third slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>CareWatch est d'une simplicité</h1>
              <!--<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>-->
              <p><a class="btn btn-lg btn-primary" href="#Simplicite" role="button">En savoir plus</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4" id="Fugue">
          <img class="img-circle" src="images/logoCircle/logoMap.png" alt="Logo carte" width="140" height="140">
          <h2>Localisation</h2>
          <p>Elle permet de localiser le patient à l'intérieur de la résidence ou de la propriété aux moyens des bornes WiFi installées à plusieurs endroits. Si la personne sort du périmètre défini, une alerte visuelle et vibratoire est envoyée sur le smartphone du soignant.</p>
          <!--<p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>-->
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="images/logoCircle/glass-of-water.jpg" alt="verre d'eau" width="140" height="140">
          <h2>Besoins primaires</h2>
          <p>Dans le cadre du bien-être de la personne âgée, boire de l'eau à intervalle régulier est important. Notre produit permet de lui rappeler de boire dès que possible. Si elle n’a pas bu après un certain laps de temps une alerte est envoyée sur le smartphone du soignant.</p>
          <!--<p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>-->
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="images/logoCircle/alarm2.png" alt="alarme" width="140" height="140">
          <h2>Rappel de médicaments</h2>
          <p>Notre produit permet de gérer le rappel de la prise de médicaments pour les personnes les prenant à heures fixes. Dès que le signal vibrant et lumineux (dessin simple) du bracelet s'enclenche, elle doit prendre ses médicaments. Après avoir pris ses médicaments dans son pilulier, elle appuie sur le bracelet pour signaler que l’action a bien été effectuée.</p>
          <!--<p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>-->
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading" id="Simplicite">Aide aux personnes âgées <span class="text-muted"></span></h2>
          <p class="lead">Les besoins de la personne âgée sont vastes et complexes. Dans l’optique d’aider la personne âgée et de soulager le personnel médical, nous avons créé CareWatch. CareWatch est un produit au design simple et efficace. Il permettra au personnel médical de savoir depuis un smartphone si la personne âgée n’est pas sortie sans l’autorisation, c’est bien hydraté et pris c’est médicament.</p> <!--+ slogant à trouver</p>-->
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" src="images/description/personnesA.jpg" alt="groupePersonneAgee">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">Savoir se démarquer<span class="text-muted"> de la concurrence.</span></h2>
          <p class="lead">Notre particularité vis-à-vis de la concurrence est qu'avec ce système particulier, les personnes âgées oubliant par négligence de boire de l'eau seront rappelées et le cas échant, un message sera envoyé au personnel pour que celui-ci l'incite à s'hydrater. Cette application offre au personnel une aide non négligeable tant pour le bien-être du patient que pour sa sécurité.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" src="images/description/light.png" alt="ampoule allumée">
        </div>
      </div>

      <hr class="featurette-divider">

      <!--<div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="holder.js/500x500/auto" alt="Generic placeholder image">
        </div>
      </div>-->

      <hr class="featurette-divider">

      <!-- /END THE FEATURETTES -->


      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2016 CareWatch, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a> &middot; <a href="http://getbootstrap.com/examples/carousel/">Template Carousel BootStrap</a></p>
      </footer>

    </div><!-- /.container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="bootstrap/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="bootstrap/js/bootstrap.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="bootstrap/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
