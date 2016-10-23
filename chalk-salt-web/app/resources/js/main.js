function isScrolledIntoView(elem) {
  var docViewTop = $(window).scrollTop();
  var docViewBottom = docViewTop + $(window).height();
  var elemTop = $(elem).offset().top + 30;
  var elemBottom = elemTop + $(elem).height();
  return ((elemBottom <= docViewBottom) && (elemTop >= docViewTop));
}
$(document).ready(function() {
  "use strict";
  var wow = new WOW({
    mobile : false
  });
  wow.init();
//  $(".header-bottom").sticky({
//    topSpacing : 0
//  });
  $(".navbar-nav").singlePageNav({
    offset : $('.header-bottom').outerHeight(),
    filter : ':not(.external)',
    speed : 1200,
    currentClass : 'active',
    easing : 'easeInOutExpo',
    updateHash : false
  });
  $(".navigation .navbar-toggle").on("click", function() {
    $(this).toggleClass("active");
  });
  
  $(window).scroll(function() {
    $('.sec-head').each(function() {
      if (isScrolledIntoView(this) === true) {
        $(this).addClass('in-view')
      } else {
        $(this).removeClass('in-view')
      }
    });
  });
  $(window).scroll(function() {
    if ($(window).scrollTop() > 400) {
      $("#back-top").fadeIn(200)
    } else {
      $("#back-top").fadeOut(200)
    }
  });
  $("#back-top").click(function() {
    $("html, body").stop().animate({
      scrollTop : 0
    }, 1500, "easeInOutExpo")
  });
});

