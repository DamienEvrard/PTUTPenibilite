let ChartColor = ["#5D62B4", "#54C3BE", "#EF726F", "#F9C446", "rgb(93.0, 98.0, 180.0)", "#21B7EC", "#04BCCC"];
let primaryColor = getComputedStyle(document.body).getPropertyValue('--primary');
let secondaryColor = getComputedStyle(document.body).getPropertyValue('--secondary');
let successColor = getComputedStyle(document.body).getPropertyValue('--success');
let warningColor = getComputedStyle(document.body).getPropertyValue('--warning');
let dangerColor = getComputedStyle(document.body).getPropertyValue('--danger');
let infoColor = getComputedStyle(document.body).getPropertyValue('--info');
let darkColor = getComputedStyle(document.body).getPropertyValue('--dark');
let lightColor = getComputedStyle(document.body).getPropertyValue('--light');

(function($) {
  'use strict';
  $(function() {
    let body = $('body');
    let contentWrapper = $('.content-wrapper');
    let scroller = $('.container-scroller');
    let footer = $('.footer');
    let sidebar = $('.sidebar');
    let formModifyPiece = $('#modifyPiece');
    let boolFormModifyPiece = false;


    formModifyPiece.css('display','none');

    $('#btnPieceModify').click(function () {
      console.log("click",boolFormModifyPiece);
      if(boolFormModifyPiece){
        formModifyPiece.css('display','none');
      }else {
        formModifyPiece.removeAttr('style');
      }
      boolFormModifyPiece = !boolFormModifyPiece;
    });

    //Close other submenu in sidebar on opening any
    sidebar.on('show.bs.collapse', '.collapse', function() {
      sidebar.find('.collapse.show').collapse('hide');
    });



    //Change sidebar and content-wrapper height
    applyStyles();

    function applyStyles() {
      //Applying perfect scrollbar
      if (!body.hasClass("rtl")) {
        if (body.hasClass("sidebar-fixed")) {
          var fixedSidebarScroll = new PerfectScrollbar('#sidebar .nav');
        }
      }
    }

    $('[data-toggle="minimize"]').on("click", function() {
      if ((body.hasClass('sidebar-toggle-display')) || (body.hasClass('sidebar-absolute'))) {
        body.toggleClass('sidebar-hidden');
      } else {
        body.toggleClass('sidebar-icon-only');
      }
    });

    //checkbox and radios
    $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');

    //fullscreen
    $("#fullscreen-button").on("click", function toggleFullScreen() {
      if ((document.fullScreenElement !== undefined && document.fullScreenElement === null) || (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || (document.mozFullScreen !== undefined && !document.mozFullScreen) || (document.webkitIsFullScreen !== undefined && !document.webkitIsFullScreen)) {
        if (document.documentElement.requestFullScreen) {
          document.documentElement.requestFullScreen();
        } else if (document.documentElement.mozRequestFullScreen) {
          document.documentElement.mozRequestFullScreen();
        } else if (document.documentElement.webkitRequestFullScreen) {
          document.documentElement.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
        } else if (document.documentElement.msRequestFullscreen) {
          document.documentElement.msRequestFullscreen();
        }
      } else {
        if (document.cancelFullScreen) {
          document.cancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      }
    })
  });
})(jQuery);