// TO SHOW NAVBAR AN FOOTER AND HEADER FOR INSTANCE
  $(function(){
    var includes = $('[data-include]');
    jQuery.each(includes, function(){
      var file = 'views/' + $(this).data('include') + '.html';
      $(this).load(file);
    });
  });

//<div data-include="header"></div>
//<div data-include="footer"></div>