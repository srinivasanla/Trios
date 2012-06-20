
/*---------------			CUFON			---------------*/

Cufon.replace('h1');
Cufon.replace('h2');
Cufon.replace('h3');
Cufon.replace('h4');

jQuery.noConflict()
jQuery(document).ready(function() {

/*---------------			NIVO SLIDER			---------------*/

	jQuery('#slideshow').nivoSlider({
		effect:'random', //Specify sets like: 'fold,fade,sliceDown'
		slices:15,
		animSpeed:500,
		pauseTime:8000,
		directionNav:false, //Next & Prev
		controlNav:true, //1,2,3...
		captionOpacity:0.8, //Universal caption opacity
		beforeChange: function() {
			var h = jQuery('#slideshow .nivo-caption').height() + 40;
			jQuery('#slideshow .nivo-caption').animate({
				'bottom' : '-' + h + 'px'
			}, 500);
		},
		afterChange: function() {
			jQuery('#slideshow .nivo-caption').animate({
				'bottom' : 0
			}, 500);
		}
	});

/*---------------			HOMEPAGE TABS			---------------*/

	jQuery('#tabs').children('div:not(:first)').hide();
	jQuery('#tabs').children('div:first').show();
	jQuery('#tab_nav li:first').addClass('current');;
	jQuery('#tab_nav li a').click(function(){
		jQuery('#tab_nav li').removeClass('current');
		jQuery(this).parent('li').addClass('current');
		href = jQuery(this).attr('href').split('#')[1];
		jQuery('#tabs').children('div:not(#' + href + ')').hide();
		if (jQuery.browser.msie && jQuery.browser.version.substr(0,3) == "6.0") {
			jQuery('#' + href).show();
		} else jQuery('#' + href).fadeIn();
		return false;
	});

/*---------------			FANCYBOX			---------------*/

	jQuery('#gallery .gallery_tab ul.photos_list li a').fancybox({
		'overlayColor' : '#000',
		'overlayOpacity' : 0.65,
		'titlePosition' : 'over'
	});
	jQuery('a.fancybox').fancybox({
		'overlayColor' : '#000',
		'overlayOpacity' : 0.65,
		'titlePosition' : 'over'
	});

/*---------------			DROP DOWN MENU			---------------*/

	jQuery('#nav li ul li').hover(function() {
		var q = jQuery(this).parent('ul').width();
		if(jQuery(this).children('ul').length) {
			var k = jQuery(this).children('ul');
      		k.attr('style', 'display: block !important;' );  // Fix for IE
			k.eq(0).css('left', -10000 );  // Fix for IE
			var w = k.width();
			k.attr('style', '');  // Fix for IE
			k.css({
				'width' : w,
				'position' : 'absolute',
				'margin' : 0,
				'top' : '-15px',
				'left' : q - 2
			});
		}
	});
	
/*---------------			IE6 NAVIGATION FIX			---------------*/

	jQuery('#nav li').hover(function() {
		jQuery(this).addClass('hover');
	}, function() {
		jQuery(this).removeClass('hover');
	});

/*---------------			CONTACT FORM			---------------*/

	jQuery('#submit').click(function() {
		var name = jQuery('#name').val();
		var email = jQuery('#email').val();
		var message = jQuery('#message').val();
		var ip = jQuery('#ip').val();
		var i = 1;
		jQuery('#response').append('<img src="images/ajax-loader.gif" alt="Loading." /> Please Wait...');
		jQuery.ajax({
			type: 'post',
			url: 'php/sending.php',
			dataType: 'json',
			data: 'name=' + name + '&email=' + email + '&message=' + message + '&ip=' + ip,
			success: function(results) {
				jQuery('#response').html(results.response).fadeIn();
			}
		});
		return false;
	});

/*---------------			BEGINS GALLERY SCRIPTS			---------------*/

	if (jQuery('body').hasClass('gallery')) {
	
/*---------------			GALLERY CATEGORIES			---------------*/

		jQuery('#gallery_nav').show();
		jQuery('#gallery .gallery_tab').show();
		jQuery('#gallery').children('div').hide();
		var hash = window.location.hash.substr(1);
		if (!hash)
			hash = 1;
		jQuery('#gallery #tab-' + hash).show();
		jQuery('#gallery_nav li:nth-child(' + hash + ')').addClass('current');
		jQuery('#gallery .gallery_stats').show();
		jQuery('.paginate ul.photos_list').hide();
		jQuery('#tab-' + hash + ' .photos_list:first').show();
		jQuery('#tab-' + hash + ' .pagination li:first').addClass('current');
	
		jQuery('#gallery_nav li a').click(function(){
			hash = jQuery(this).parent('li').index() + 1;
			window.location.hash = hash;
			jQuery('.paginate ul.photos_list').hide();
			jQuery('#tab-' + hash + ' .photos_list:first').show();
			jQuery('#tab-' + hash + ' .pagination li:first').addClass('current');
			jQuery('.pagination li').removeClass('current');
			jQuery('#tab-' + hash + ' .pagination li:first').addClass('current');
			jQuery('#gallery_nav li').removeClass('current');
			jQuery(this).parent('li').addClass('current');
			href = jQuery(this).attr('href').split('#')[1];
			jQuery('#gallery').children('div:not(#' + href + ')').hide();
			if (jQuery.browser.msie && jQuery.browser.version.substr(0,3) == "6.0") {
				jQuery('#' + href).show();
			} else jQuery('#' + href).fadeIn();
			return false;
		});

/*---------------			GALLERY PAGINATION			---------------*/
	
		jQuery('.pagination li a').click(function() {
			var t = jQuery(this).text();
			jQuery('.pagination li').removeClass('current');
			jQuery(this).parent('li').addClass('current');
			jQuery('.paginate ul.photos_list').hide();
			jQuery('#tab-' + hash + ' ul.photos_list:nth-child(' + t + ')').fadeIn();
			return false;
		});
	
	}
	
});