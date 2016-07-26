package com.charlesmadere.hummingbird.misc;

import android.os.Build;

import com.charlesmadere.hummingbird.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.M)
public class JsoupUtilsTest {

    private static final String CSRF_TOKEN = "jL/7itrsQB+9gAHi5ciaGSdxSQeqA5XnNvN8EGm3Fzo=";
    private static final String SIGN_IN_PAGE = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head prefix='og: http://ogp.me/ns# fb: http://ogp.me/ns/fb# hummingbird: http://ogp.me/ns/fb/hummingbird#'>\n" +
            "<title>Hummingbird</title>\n" +
            "<script type=\"text/javascript\">\n" +
            "//<![CDATA[\n" +
            "try{if (!window.CloudFlare) {var CloudFlare=[{verbose:0,p:0,byc:0,owlid:\"cf\",bag2:1,mirage2:0,oracle:0,paths:{cloudflare:\"/cdn-cgi/nexp/dok3v=1613a3a185/\"},atok:\"3f76740cb2b6b1ee732cf6d9b35437a7\",petok:\"09db9c2f0801ab7fb555806dc8e52d96104e8151-1469413517-1800\",zone:\"hummingbird.me\",rocket:\"0\",apps:{\"prosperlinks\":{\"domain_id\":\"3495982\",\"account_id\":\"72166\",\"pl_linkOptimizerActive\":\"0\"}},sha2test:0}];!function(a,b){a=document.createElement(\"script\"),b=document.getElementsByTagName(\"script\")[0],a.async=!0,a.src=\"//ajax.cloudflare.com/cdn-cgi/nexp/dok3v=0489c402f5/cloudflare.min.js\",b.parentNode.insertBefore(a,b)}()}}catch(e){};\n" +
            "//]]>\n" +
            "</script>\n" +
            "<link href=\"/assets/application-a9cab1b765ab3de188ddf6a64559655a.css\" media=\"all\" rel=\"stylesheet\" />\n" +
            "<meta content=\"authenticity_token\" name=\"csrf-param\" />\n" +
            "<meta content=\"jL/7itrsQB+9gAHi5ciaGSdxSQeqA5XnNvN8EGm3Fzo=\" name=\"csrf-token\" />\n" +
            "<meta content='width=device-width, initial-scale=1.0' name='viewport'>\n" +
            "<meta content='325314560922421' property='fb:app_id'>\n" +
            "<meta content='!' name='fragment'>\n" +
            "<meta content='Hummingbird' name='og:title'>\n" +
            "\n" +
            "<script>\n" +
            "  // Twitter\n" +
            "  !function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');\n" +
            "  \n" +
            "  // Facebook\n" +
            "  (function(d, s, id) {\n" +
            "    var js, fjs = d.getElementsByTagName(s)[0];\n" +
            "    if (d.getElementById(id)) return;\n" +
            "    js = d.createElement(s); js.id = id;\n" +
            "    js.src = \"//connect.facebook.net/en_US/all.js#xfbml=1&appId=452427161479702\";\n" +
            "    fjs.parentNode.insertBefore(js, fjs);\n" +
            "  }(document, 'script', 'facebook-jssdk'));\n" +
            "</script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<noscript>\n" +
            "<nav class='navbar navbar-inverse hb-nav navbar-fixed-top'>\n" +
            "<div class='container'>\n" +
            "<a class='navbar-brand' href='/'>\n" +
            "<img alt='Hummingbird' height='20px' src='/assets/logo-new.jpg' width='119px'>\n" +
            "</a>\n" +
            "<ul class='nav navbar-nav'>\n" +
            "<li class='dropdown'>\n" +
            "<a href='/anime'>Trending Anime</a>\n" +
            "</li>\n" +
            "<li class='dropdown'>\n" +
            "<a href='/anime/upcoming'>Upcoming Anime</a>\n" +
            "</li>\n" +
            "<li class='dropdown'>\n" +
            "<a href='https://forums.hummingbird.me/'>Community</a>\n" +
            "</li>\n" +
            "</ul>\n" +
            "</div>\n" +
            "</nav>\n" +
            "\n" +
            "</noscript>\n" +
            "<script>\n" +
            "  (function(){\n" +
            "    var bsa = document.createElement('script');\n" +
            "       bsa.type = 'text/javascript';\n" +
            "       bsa.async = true;\n" +
            "       bsa.src = 'https://s3.buysellads.com/ac/bsa.js';\n" +
            "    (document.getElementsByTagName('head')[0]||document.getElementsByTagName('body')[0]).appendChild(bsa);\n" +
            "  })();\n" +
            "</script>\n" +
            "<div id='fb-root'></div>\n" +
            "<!-- Preload Models -->\n" +
            "<script>\n" +
            "  window.currentUserName = null;\n" +
            "  window.preloadData = [];\n" +
            "  window.genericPreload = {\"blotter\":null,\"stripe_key\":\"pk_live_KCWl15JHhrNwnd4r45CVNMMY\",\"emoji\":{\":smile:\":\"1f604\",\":laughing:\":\"1f606\",\":blush:\":\"1f60a\",\":smiley:\":\"1f603\",\":relaxed:\":\"263a\",\":smirk:\":\"1f60f\",\":heart_eyes:\":\"1f60d\",\":kissing_heart:\":\"1f618\",\":kissing_closed_eyes:\":\"1f61a\",\":flushed:\":\"1f633\",\":relieved:\":\"1f625\",\":satisfied:\":\"1f60c\",\":grin:\":\"1f601\",\":wink:\":\"1f609\",\":stuck_out_tongue_winking_eye:\":\"1f61c\",\":stuck_out_tongue_closed_eyes:\":\"1f61d\",\":grinning:\":\"1f600\",\":kissing:\":\"1f617\",\":kissing_smiling_eyes:\":\"1f619\",\":stuck_out_tongue:\":\"1f61b\",\":sleeping:\":\"1f634\",\":worried:\":\"1f61f\",\":frowning:\":\"1f626\",\":anguished:\":\"1f627\",\":open_mouth:\":\"1f62e\",\":grimacing:\":\"1f62c\",\":confused:\":\"1f615\",\":hushed:\":\"1f62f\",\":expressionless:\":\"1f611\",\":unamused:\":\"1f612\",\":sweat_smile:\":\"1f605\",\":sweat:\":\"1f613\",\":weary:\":\"1f629\",\":pensive:\":\"1f614\",\":disappointed:\":\"1f61e\",\":confounded:\":\"1f616\",\":fearful:\":\"1f628\",\":cold_sweat:\":\"1f630\",\":persevere:\":\"1f623\",\":cry:\":\"1f622\",\":sob:\":\"1f62d\",\":joy:\":\"1f602\",\":astonished:\":\"1f632\",\":scream:\":\"1f631\",\":tired_face:\":\"1f62b\",\":angry:\":\"1f620\",\":rage:\":\"1f621\",\":triumph:\":\"1f624\",\":sleepy:\":\"1f62a\",\":yum:\":\"1f60b\",\":mask:\":\"1f637\",\":sunglasses:\":\"1f60e\",\":dizzy_face:\":\"1f635\",\":imp:\":\"1f47f\",\":smiling_imp:\":\"1f608\",\":neutral_face:\":\"1f610\",\":no_mouth:\":\"1f636\",\":innocent:\":\"1f607\",\":alien:\":\"1f47d\",\":yellow_heart:\":\"1f49b\",\":blue_heart:\":\"1f499\",\":purple_heart:\":\"1f49c\",\":heart:\":\"2764\",\":green_heart:\":\"1f49a\",\":broken_heart:\":\"1f494\",\":heartbeat:\":\"1f493\",\":heartpulse:\":\"1f497\",\":two_hearts:\":\"1f495\",\":revolving_hearts:\":\"1f49e\",\":cupid:\":\"1f498\",\":sparkling_heart:\":\"1f496\",\":sparkles:\":\"2728\",\":star:\":\"2b50\",\":star2:\":\"1f31f\",\":dizzy:\":\"1f4ab\",\":boom:\":\"1f4a5\",\":anger:\":\"1f4a2\",\":exclamation:\":\"2757\",\":question:\":\"2753\",\":grey_exclamation:\":\"2755\",\":grey_question:\":\"2754\",\":zzz:\":\"1f4a4\",\":dash:\":\"1f4a8\",\":sweat_drops:\":\"1f4a6\",\":notes:\":\"1f3b6\",\":musical_note:\":\"1f3b5\",\":fire:\":\"1f525\",\":poop:\":\"1f4a9\",\":thumbsup:\":\"1f44d\",\":thumbsdown:\":\"1f44e\",\":ok_hand:\":\"1f44c\",\":punch:\":\"1f44a\",\":fist:\":\"270a\",\":v:\":\"270c\",\":wave:\":\"1f44b\",\":hand:\":\"270b\",\":open_hands:\":\"1f450\",\":point_up:\":\"261d\",\":point_down:\":\"1f447\",\":point_left:\":\"1f448\",\":point_right:\":\"1f449\",\":raised_hands:\":\"1f64c\",\":pray:\":\"1f64f\",\":point_up_2:\":\"1f446\",\":clap:\":\"1f44f\",\":muscle:\":\"1f4aa\",\":walking:\":\"1f6b6\",\":runner:\":\"1f3c3\",\":couple:\":\"1f46b\",\":family:\":\"1f46a\",\":two_men_holding_hands:\":\"1f46c\",\":two_women_holding_hands:\":\"1f46d\",\":dancer:\":\"1f483\",\":dancers:\":\"1f46f\",\":ok_woman:\":\"1f646\",\":no_good:\":\"1f645\",\":information_desk_person:\":\"1f481\",\":raised_hand:\":\"1f64b\",\":bride_with_veil:\":\"1f470\",\":person_with_pouting_face:\":\"1f64e\",\":person_frowning:\":\"1f64d\",\":bow:\":\"1f647\",\":couplekiss:\":\"1f48f\",\":couple_with_heart:\":\"1f491\",\":massage:\":\"1f486\",\":haircut:\":\"1f487\",\":nail_care:\":\"1f485\",\":boy:\":\"1f466\",\":girl:\":\"1f467\",\":woman:\":\"1f469\",\":man:\":\"1f468\",\":baby:\":\"1f476\",\":older_woman:\":\"1f475\",\":older_man:\":\"1f474\",\":person_with_blond_hair:\":\"1f471\",\":man_with_gua_pi_mao:\":\"1f472\",\":man_with_turban:\":\"1f473\",\":construction_worker:\":\"1f477\",\":cop:\":\"1f46e\",\":angel:\":\"1f47c\",\":princess:\":\"1f478\",\":smiley_cat:\":\"1f63a\",\":smile_cat:\":\"1f638\",\":heart_eyes_cat:\":\"1f63b\",\":kissing_cat:\":\"1f63d\",\":smirk_cat:\":\"1f63c\",\":scream_cat:\":\"1f640\",\":crying_cat_face:\":\"1f63f\",\":joy_cat:\":\"1f639\",\":pouting_cat:\":\"1f63e\",\":japanese_ogre:\":\"1f479\",\":japanese_goblin:\":\"1f47a\",\":see_no_evil:\":\"1f648\",\":hear_no_evil:\":\"1f649\",\":speak_no_evil:\":\"1f64a\",\":guardsman:\":\"1f482\",\":skull:\":\"1f480\",\":feet:\":\"1f463\",\":lips:\":\"1f444\",\":kiss:\":\"1f48b\",\":droplet:\":\"1f4a7\",\":ear:\":\"1f442\",\":eyes:\":\"1f440\",\":nose:\":\"1f443\",\":tongue:\":\"1f445\",\":love_letter:\":\"1f48c\",\":bust_in_silhouette:\":\"1f464\",\":busts_in_silhouette:\":\"1f465\",\":speech_balloon:\":\"1f4ac\",\":thought_balloon:\":\"1f4ad\",\":sunny:\":\"2600\",\":umbrella:\":\"2614\",\":cloud:\":\"2601\",\":snowflake:\":\"2744\",\":snowman:\":\"26c4\",\":zap:\":\"26a1\",\":cyclone:\":\"1f300\",\":foggy:\":\"1f301\",\":ocean:\":\"1f30a\",\":cat:\":\"1f431\",\":dog:\":\"1f436\",\":mouse:\":\"1f42d\",\":hamster:\":\"1f439\",\":rabbit:\":\"1f430\",\":wolf:\":\"1f43a\",\":frog:\":\"1f438\",\":tiger:\":\"1f42f\",\":koala:\":\"1f428\",\":bear:\":\"1f43b\",\":pig:\":\"1f437\",\":pig_nose:\":\"1f43d\",\":cow:\":\"1f42e\",\":boar:\":\"1f417\",\":monkey_face:\":\"1f435\",\":monkey:\":\"1f412\",\":horse:\":\"1f434\",\":racehorse:\":\"1f40e\",\":camel:\":\"1f42b\",\":sheep:\":\"1f411\",\":elephant:\":\"1f418\",\":panda_face:\":\"1f43c\",\":snake:\":\"1f40d\",\":bird:\":\"1f426\",\":baby_chick:\":\"1f424\",\":hatched_chick:\":\"1f425\",\":hatching_chick:\":\"1f423\",\":chicken:\":\"1f414\",\":penguin:\":\"1f427\",\":turtle:\":\"1f422\",\":bug:\":\"1f41b\",\":honeybee:\":\"1f41d\",\":ant:\":\"1f41c\",\":beetle:\":\"1f41e\",\":snail:\":\"1f40c\",\":octopus:\":\"1f419\",\":tropical_fish:\":\"1f420\",\":fish:\":\"1f41f\",\":whale:\":\"1f433\",\":whale2:\":\"1f40b\",\":dolphin:\":\"1f42c\",\":cow2:\":\"1f404\",\":ram:\":\"1f40f\",\":rat:\":\"1f400\",\":water_buffalo:\":\"1f403\",\":tiger2:\":\"1f405\",\":rabbit2:\":\"1f407\",\":dragon:\":\"1f409\",\":goat:\":\"1f410\",\":rooster:\":\"1f413\",\":dog2:\":\"1f415\",\":pig2:\":\"1f416\",\":mouse2:\":\"1f401\",\":ox:\":\"1f402\",\":dragon_face:\":\"1f432\",\":blowfish:\":\"1f421\",\":crocodile:\":\"1f40a\",\":dromedary_camel:\":\"1f42a\",\":leopard:\":\"1f406\",\":cat2:\":\"1f408\",\":poodle:\":\"1f429\",\":paw_prints:\":\"1f43e\",\":bouquet:\":\"1f490\",\":cherry_blossom:\":\"1f338\",\":tulip:\":\"1f337\",\":four_leaf_clover:\":\"1f340\",\":rose:\":\"1f339\",\":sunflower:\":\"1f33b\",\":hibiscus:\":\"1f33a\",\":maple_leaf:\":\"1f341\",\":leaves:\":\"1f343\",\":fallen_leaf:\":\"1f342\",\":herb:\":\"1f33f\",\":mushroom:\":\"1f344\",\":cactus:\":\"1f335\",\":palm_tree:\":\"1f334\",\":evergreen_tree:\":\"1f332\",\":deciduous_tree:\":\"1f333\",\":chestnut:\":\"1f330\",\":seedling:\":\"1f331\",\":blossom:\":\"1f33c\",\":ear_of_rice:\":\"1f33e\",\":shell:\":\"1f41a\",\":globe_with_meridians:\":\"1f310\",\":sun_with_face:\":\"1f31e\",\":full_moon_with_face:\":\"1f31d\",\":new_moon_with_face:\":\"1f31a\",\":new_moon:\":\"1f311\",\":waxing_crescent_moon:\":\"1f312\",\":first_quarter_moon:\":\"1f313\",\":waxing_gibbous_moon:\":\"1f314\",\":full_moon:\":\"1f315\",\":waning_gibbous_moon:\":\"1f316\",\":last_quarter_moon:\":\"1f317\",\":waning_crescent_moon:\":\"1f318\",\":last_quarter_moon_with_face:\":\"1f31c\",\":first_quarter_moon_with_face:\":\"1f31b\",\":moon:\":\"1f319\",\":earth_africa:\":\"1f30d\",\":earth_americas:\":\"1f30e\",\":earth_asia:\":\"1f30f\",\":volcano:\":\"1f30b\",\":milky_way:\":\"1f30c\",\":partly_sunny:\":\"26c5\",\":bamboo:\":\"1f38d\",\":gift_heart:\":\"1f49d\",\":dolls:\":\"1f38e\",\":school_satchel:\":\"1f392\",\":mortar_board:\":\"1f393\",\":flags:\":\"1f38f\",\":fireworks:\":\"1f386\",\":sparkler:\":\"1f387\",\":wind_chime:\":\"1f390\",\":rice_scene:\":\"1f391\",\":jack_o_lantern:\":\"1f383\",\":ghost:\":\"1f47b\",\":santa:\":\"1f385\",\":8ball:\":\"1f3b1\",\":alarm_clock:\":\"23f0\",\":apple:\":\"1f34e\",\":art:\":\"1f3a8\",\":baby_bottle:\":\"1f37c\",\":balloon:\":\"1f388\",\":banana:\":\"1f34c\",\":bar_chart:\":\"1f4ca\",\":baseball:\":\"26be\",\":basketball:\":\"1f3c0\",\":bath:\":\"1f6c0\",\":bathtub:\":\"1f6c1\",\":battery:\":\"1f50b\",\":beer:\":\"1f37a\",\":beers:\":\"1f37b\",\":bell:\":\"1f514\",\":bento:\":\"1f371\",\":bicyclist:\":\"1f6b4\",\":bikini:\":\"1f459\",\":birthday:\":\"1f382\",\":black_joker:\":\"1f0cf\",\":black_nib:\":\"2712\",\":blue_book:\":\"1f4d8\",\":bomb:\":\"1f4a3\",\":bookmark:\":\"1f516\",\":bookmark_tabs:\":\"1f4d1\",\":books:\":\"1f4da\",\":boot:\":\"1f462\",\":bowling:\":\"1f3b3\",\":bread:\":\"1f35e\",\":briefcase:\":\"1f4bc\",\":bulb:\":\"1f4a1\",\":cake:\":\"1f370\",\":calendar:\":\"1f4c6\",\":calling:\":\"1f4f2\",\":camera:\":\"1f4f7\",\":candy:\":\"1f36c\",\":card_index:\":\"1f4c7\",\":cd:\":\"1f4bf\",\":chart_with_downwards_trend:\":\"1f4c9\",\":chart_with_upwards_trend:\":\"1f4c8\",\":cherries:\":\"1f352\",\":chocolate_bar:\":\"1f36b\",\":christmas_tree:\":\"1f384\",\":clapper:\":\"1f3ac\",\":clipboard:\":\"1f4cb\",\":closed_book:\":\"1f4d5\",\":closed_lock_with_key:\":\"1f510\",\":closed_umbrella:\":\"1f302\",\":clubs:\":\"2663\",\":cocktail:\":\"1f378\",\":coffee:\":\"2615\",\":computer:\":\"1f4bb\",\":confetti_ball:\":\"1f38a\",\":cookie:\":\"1f36a\",\":corn:\":\"1f33d\",\":credit_card:\":\"1f4b3\",\":crown:\":\"1f451\",\":crystal_ball:\":\"1f52e\",\":curry:\":\"1f35b\",\":custard:\":\"1f36e\",\":dango:\":\"1f361\",\":dart:\":\"1f3af\",\":date:\":\"1f4c5\",\":diamonds:\":\"2666\",\":dollar:\":\"1f4b5\",\":door:\":\"1f6aa\",\":doughnut:\":\"1f369\",\":dress:\":\"1f457\",\":dvd:\":\"1f4c0\",\":e_mail:\":\"1f4e7\",\":egg:\":\"1f373\",\":eggplant:\":\"1f346\",\":electric_plug:\":\"1f50c\",\":email:\":\"2709\",\":euro:\":\"1f4b6\",\":eyeglasses:\":\"1f453\",\":fax:\":\"1f4e0\",\":file_folder:\":\"1f4c1\",\":fish_cake:\":\"1f365\",\":fishing_pole_and_fish:\":\"1f3a3\",\":flashlight:\":\"1f526\",\":floppy_disk:\":\"1f4be\",\":flower_playing_cards:\":\"1f3b4\",\":football:\":\"1f3c8\",\":fork_and_knife:\":\"1f374\",\":fried_shrimp:\":\"1f364\",\":fries:\":\"1f35f\",\":game_die:\":\"1f3b2\",\":gem:\":\"1f48e\",\":gift:\":\"1f381\",\":golf:\":\"26f3\",\":grapes:\":\"1f347\",\":green_apple:\":\"1f34f\",\":green_book:\":\"1f4d7\",\":guitar:\":\"1f3b8\",\":gun:\":\"1f52b\",\":hamburger:\":\"1f354\",\":hammer:\":\"1f528\",\":handbag:\":\"1f45c\",\":headphones:\":\"1f3a7\",\":hearts:\":\"2665\",\":high_brightness:\":\"1f506\",\":high_heel:\":\"1f460\",\":hocho:\":\"1f52a\",\":honey_pot:\":\"1f36f\",\":horse_racing:\":\"1f3c7\",\":hourglass:\":\"231b\",\":hourglass_flowing_sand:\":\"23f3\",\":ice_cream:\":\"1f368\",\":icecream:\":\"1f366\",\":inbox_tray:\":\"1f4e5\",\":incoming_envelope:\":\"1f4e8\",\":iphone:\":\"1f4f1\",\":jeans:\":\"1f456\",\":key:\":\"1f511\",\":kimono:\":\"1f458\",\":ledger:\":\"1f4d2\",\":lemon:\":\"1f34b\",\":lipstick:\":\"1f484\",\":lock:\":\"1f512\",\":lock_with_ink_pen:\":\"1f50f\",\":lollipop:\":\"1f36d\",\":loop:\":\"27bf\",\":loudspeaker:\":\"1f4e2\",\":low_brightness:\":\"1f505\",\":mag:\":\"1f50d\",\":mag_right:\":\"1f50e\",\":mahjong:\":\"1f004\",\":mailbox:\":\"1f4eb\",\":mailbox_closed:\":\"1f4ea\",\":mailbox_with_mail:\":\"1f4ec\",\":mailbox_with_no_mail:\":\"1f4ed\",\":mans_shoe:\":\"1f45e\",\":meat_on_bone:\":\"1f356\",\":mega:\":\"1f4e3\",\":melon:\":\"1f348\",\":memo:\":\"1f4dd\",\":microphone:\":\"1f3a4\",\":microscope:\":\"1f52c\",\":minidisc:\":\"1f4bd\",\":money_with_wings:\":\"1f4b8\",\":moneybag:\":\"1f4b0\",\":mountain_bicyclist:\":\"1f6b5\",\":movie_camera:\":\"1f3a5\",\":musical_keyboard:\":\"1f3b9\",\":musical_score:\":\"1f3bc\",\":mute:\":\"1f507\",\":name_badge:\":\"1f4db\",\":necktie:\":\"1f454\",\":newspaper:\":\"1f4f0\",\":no_bell:\":\"1f515\",\":notebook:\":\"1f4d3\",\":notebook_with_decorative_cover:\":\"1f4d4\",\":nut_and_bolt:\":\"1f529\",\":oden:\":\"1f362\",\":open_file_folder:\":\"1f4c2\",\":orange_book:\":\"1f4d9\",\":outbox_tray:\":\"1f4e4\",\":page_facing_up:\":\"1f4c4\",\":page_with_curl:\":\"1f4c3\",\":pager:\":\"1f4df\",\":paperclip:\":\"1f4ce\",\":peach:\":\"1f351\",\":pear:\":\"1f350\",\":pencil2:\":\"270f\",\":phone:\":\"260e\",\":pill:\":\"1f48a\",\":pineapple:\":\"1f34d\",\":pizza:\":\"1f355\",\":postal_horn:\":\"1f4ef\",\":postbox:\":\"1f4ee\",\":pouch:\":\"1f45d\",\":poultry_leg:\":\"1f357\",\":pound:\":\"1f4b7\",\":purse:\":\"1f45b\",\":pushpin:\":\"1f4cc\",\":radio:\":\"1f4fb\",\":ramen:\":\"1f35c\",\":ribbon:\":\"1f380\",\":rice:\":\"1f35a\",\":rice_ball:\":\"1f359\",\":rice_cracker:\":\"1f358\",\":ring:\":\"1f48d\",\":rugby_football:\":\"1f3c9\",\":running_shirt_with_sash:\":\"1f3bd\",\":sake:\":\"1f376\",\":sandal:\":\"1f461\",\":satellite:\":\"1f4e1\",\":saxophone:\":\"1f3b7\",\":scissors:\":\"2702\",\":scroll:\":\"1f4dc\",\":seat:\":\"1f4ba\",\":shaved_ice:\":\"1f367\",\":shirt:\":\"1f455\",\":shower:\":\"1f6bf\",\":ski:\":\"1f3bf\",\":smoking:\":\"1f6ac\",\":snowboarder:\":\"1f3c2\",\":soccer:\":\"26bd\",\":sound:\":\"1f509\",\":space_invader:\":\"1f47e\",\":spades:\":\"2660\",\":spaghetti:\":\"1f35d\",\":speaker:\":\"1f50a\",\":stew:\":\"1f372\",\":straight_ruler:\":\"1f4cf\",\":strawberry:\":\"1f353\",\":surfer:\":\"1f3c4\",\":sushi:\":\"1f363\",\":sweet_potato:\":\"1f360\",\":swimmer:\":\"1f3ca\",\":syringe:\":\"1f489\",\":tada:\":\"1f389\",\":tanabata_tree:\":\"1f38b\",\":tangerine:\":\"1f34a\",\":tea:\":\"1f375\",\":telephone_receiver:\":\"1f4de\",\":telescope:\":\"1f52d\",\":tennis:\":\"1f3be\",\":toilet:\":\"1f6bd\",\":tomato:\":\"1f345\",\":tophat:\":\"1f3a9\",\":triangular_ruler:\":\"1f4d0\",\":trophy:\":\"1f3c6\",\":tropical_drink:\":\"1f379\",\":trumpet:\":\"1f3ba\",\":tv:\":\"1f4fa\",\":unlock:\":\"1f513\",\":vhs:\":\"1f4fc\",\":video_camera:\":\"1f4f9\",\":video_game:\":\"1f3ae\",\":violin:\":\"1f3bb\",\":watch:\":\"231a\",\":watermelon:\":\"1f349\",\":wine_glass:\":\"1f377\",\":womans_clothes:\":\"1f45a\",\":womans_hat:\":\"1f452\",\":wrench:\":\"1f527\",\":yen:\":\"1f4b4\",\":aerial_tramway:\":\"1f6a1\",\":airplane:\":\"2708\",\":ambulance:\":\"1f691\",\":anchor:\":\"2693\",\":articulated_lorry:\":\"1f69b\",\":atm:\":\"1f3e7\",\":bank:\":\"1f3e6\",\":barber:\":\"1f488\",\":beginner:\":\"1f530\",\":bike:\":\"1f6b2\",\":blue_car:\":\"1f699\",\":boat:\":\"26f5\",\":bridge_at_night:\":\"1f309\",\":bullettrain_front:\":\"1f685\",\":bullettrain_side:\":\"1f684\",\":bus:\":\"1f68c\",\":busstop:\":\"1f68f\",\":car:\":\"1f697\",\":carousel_horse:\":\"1f3a0\",\":checkered_flag:\":\"1f3c1\",\":church:\":\"26ea\",\":circus_tent:\":\"1f3aa\",\":city_sunrise:\":\"1f307\",\":city_sunset:\":\"1f306\",\":construction:\":\"1f6a7\",\":convenience_store:\":\"1f3ea\",\":crossed_flags:\":\"1f38c\",\":department_store:\":\"1f3ec\",\":european_castle:\":\"1f3f0\",\":european_post_office:\":\"1f3e4\",\":factory:\":\"1f3ed\",\":ferris_wheel:\":\"1f3a1\",\":fire_engine:\":\"1f692\",\":fountain:\":\"26f2\",\":fuelpump:\":\"26fd\",\":helicopter:\":\"1f681\",\":hospital:\":\"1f3e5\",\":hotel:\":\"1f3e8\",\":hotsprings:\":\"2668\",\":house:\":\"1f3e0\",\":house_with_garden:\":\"1f3e1\",\":japan:\":\"1f5fe\",\":japanese_castle:\":\"1f3ef\",\":light_rail:\":\"1f688\",\":love_hotel:\":\"1f3e9\",\":minibus:\":\"1f690\",\":monorail:\":\"1f69d\",\":mount_fuji:\":\"1f5fb\",\":mountain_cableway:\":\"1f6a0\",\":mountain_railway:\":\"1f69e\",\":moyai:\":\"1f5ff\",\":office:\":\"1f3e2\",\":oncoming_automobile:\":\"1f698\",\":oncoming_bus:\":\"1f68d\",\":oncoming_police_car:\":\"1f694\",\":oncoming_taxi:\":\"1f696\",\":performing_arts:\":\"1f3ad\",\":police_car:\":\"1f693\",\":post_office:\":\"1f3e3\",\":railway_car:\":\"1f683\",\":rainbow:\":\"1f308\",\":rocket:\":\"1f680\",\":roller_coaster:\":\"1f3a2\",\":rotating_light:\":\"1f6a8\",\":round_pushpin:\":\"1f4cd\",\":rowboat:\":\"1f6a3\",\":school:\":\"1f3eb\",\":ship:\":\"1f6a2\",\":slot_machine:\":\"1f3b0\",\":speedboat:\":\"1f6a4\",\":stars:\":\"1f303\",\":station:\":\"1f689\",\":statue_of_liberty:\":\"1f5fd\",\":steam_locomotive:\":\"1f682\",\":sunrise:\":\"1f305\",\":sunrise_over_mountains:\":\"1f304\",\":suspension_railway:\":\"1f69f\",\":taxi:\":\"1f695\",\":tent:\":\"26fa\",\":ticket:\":\"1f3ab\",\":tokyo_tower:\":\"1f5fc\",\":tractor:\":\"1f69c\",\":traffic_light:\":\"1f6a5\",\":train2:\":\"1f686\",\":tram:\":\"1f68a\",\":triangular_flag_on_post:\":\"1f6a9\",\":trolleybus:\":\"1f68e\",\":truck:\":\"1f69a\",\":vertical_traffic_light:\":\"1f6a6\",\":warning:\":\"26a0\",\":wedding:\":\"1f492\",\":jp:\":\"1f1ef-1f1f5\",\":kr:\":\"1f1f0-1f1f7\",\":cn:\":\"1f1e8-1f1f3\",\":us:\":\"1f1fa-1f1f8\",\":fr:\":\"1f1eb-1f1f7\",\":es:\":\"1f1ea-1f1f8\",\":it:\":\"1f1ee-1f1f9\",\":ru:\":\"1f1f7-1f1fa\",\":gb:\":\"1f1ec-1f1e7\",\":de:\":\"1f1e9-1f1ea\",\":100:\":\"1f4af\",\":1234:\":\"1f522\",\":a:\":\"1f170\",\":ab:\":\"1f18e\",\":abc:\":\"1f524\",\":abcd:\":\"1f521\",\":accept:\":\"1f251\",\":aquarius:\":\"2652\",\":aries:\":\"2648\",\":arrow_backward:\":\"25c0\",\":arrow_double_down:\":\"23ec\",\":arrow_double_up:\":\"23eb\",\":arrow_down:\":\"2b07\",\":arrow_down_small:\":\"1f53d\",\":arrow_forward:\":\"25b6\",\":arrow_heading_down:\":\"2935\",\":arrow_heading_up:\":\"2934\",\":arrow_left:\":\"2b05\",\":arrow_lower_left:\":\"2199\",\":arrow_lower_right:\":\"2198\",\":arrow_right:\":\"27a1\",\":arrow_right_hook:\":\"21aa\",\":arrow_up:\":\"2b06\",\":arrow_up_down:\":\"2195\",\":arrow_up_small:\":\"1f53c\",\":arrow_upper_left:\":\"2196\",\":arrow_upper_right:\":\"2197\",\":arrows_clockwise:\":\"1f503\",\":arrows_counterclockwise:\":\"1f504\",\":b:\":\"1f171\",\":baby_symbol:\":\"1f6bc\",\":baggage_claim:\":\"1f6c4\",\":ballot_box_with_check:\":\"2611\",\":bangbang:\":\"203c\",\":black_circle:\":\"26ab\",\":black_square_button:\":\"1f532\",\":cancer:\":\"264b\",\":capital_abcd:\":\"1f520\",\":capricorn:\":\"2651\",\":chart:\":\"1f4b9\",\":children_crossing:\":\"1f6b8\",\":cinema:\":\"1f3a6\",\":cl:\":\"1f191\",\":clock1:\":\"1f550\",\":clock10:\":\"1f559\",\":clock1030:\":\"1f565\",\":clock11:\":\"1f55a\",\":clock1130:\":\"1f566\",\":clock12:\":\"1f55b\",\":clock1230:\":\"1f567\",\":clock130:\":\"1f55c\",\":clock2:\":\"1f551\",\":clock230:\":\"1f55d\",\":clock3:\":\"1f552\",\":clock330:\":\"1f55e\",\":clock4:\":\"1f553\",\":clock430:\":\"1f55f\",\":clock5:\":\"1f554\",\":clock530:\":\"1f560\",\":clock6:\":\"1f555\",\":clock630:\":\"1f561\",\":clock7:\":\"1f556\",\":clock730:\":\"1f562\",\":clock8:\":\"1f557\",\":clock830:\":\"1f563\",\":clock9:\":\"1f558\",\":clock930:\":\"1f564\",\":congratulations:\":\"3297\",\":cool:\":\"1f192\",\":copyright:\":\"a9\",\":curly_loop:\":\"27b0\",\":currency_exchange:\":\"1f4b1\",\":customs:\":\"1f6c3\",\":diamond_shape_with_a_dot_inside:\":\"1f4a0\",\":do_not_litter:\":\"1f6af\",\":eight:\":\"38-20e3\",\":eight_pointed_black_star:\":\"2734\",\":eight_spoked_asterisk:\":\"2733\",\":end:\":\"1f51a\",\":fast_forward:\":\"23e9\",\":five:\":\"35-20e3\",\":four:\":\"34-20e3\",\":free:\":\"1f193\",\":gemini:\":\"264a\",\":hash:\":\"23-20e3\",\":heart_decoration:\":\"1f49f\",\":heavy_check_mark:\":\"2714\",\":heavy_division_sign:\":\"2797\",\":heavy_dollar_sign:\":\"1f4b2\",\":heavy_minus_sign:\":\"2796\",\":heavy_multiplication_x:\":\"2716\",\":heavy_plus_sign:\":\"2795\",\":id:\":\"1f194\",\":ideograph_advantage:\":\"1f250\",\":information_source:\":\"2139\",\":interrobang:\":\"2049\",\":keycap_ten:\":\"1f51f\",\":koko:\":\"1f201\",\":large_blue_circle:\":\"1f535\",\":large_blue_diamond:\":\"1f537\",\":large_orange_diamond:\":\"1f536\",\":left_luggage:\":\"1f6c5\",\":left_right_arrow:\":\"2194\",\":leftwards_arrow_with_hook:\":\"21a9\",\":leo:\":\"264c\",\":libra:\":\"264e\",\":link:\":\"1f517\",\":m:\":\"24c2\",\":mens:\":\"1f6b9\",\":metro:\":\"1f687\",\":mobile_phone_off:\":\"1f4f4\",\":negative_squared_cross_mark:\":\"274e\",\":new:\":\"1f195\",\":ng:\":\"1f196\",\":nine:\":\"39-20e3\",\":no_bicycles:\":\"1f6b3\",\":no_entry:\":\"26d4\",\":no_entry_sign:\":\"1f6ab\",\":no_mobile_phones:\":\"1f4f5\",\":no_pedestrians:\":\"1f6b7\",\":no_smoking:\":\"1f6ad\",\":non_potable_water:\":\"1f6b1\",\":o:\":\"2b55\",\":o2:\":\"1f17e\",\":ok:\":\"1f197\",\":on:\":\"1f51b\",\":one:\":\"31-20e3\",\":ophiuchus:\":\"26ce\",\":parking:\":\"1f17f\",\":part_alternation_mark:\":\"303d\",\":passport_control:\":\"1f6c2\",\":pisces:\":\"2653\",\":potable_water:\":\"1f6b0\",\":put_litter_in_its_place:\":\"1f6ae\",\":radio_button:\":\"1f518\",\":recycle:\":\"267b\",\":red_circle:\":\"1f534\",\":registered:\":\"ae\",\":repeat:\":\"1f501\",\":repeat_one:\":\"1f502\",\":restroom:\":\"1f6bb\",\":rewind:\":\"23ea\",\":sa:\":\"1f202\",\":sagittarius:\":\"2650\",\":scorpius:\":\"264f\",\":secret:\":\"3299\",\":seven:\":\"37-20e3\",\":signal_strength:\":\"1f4f6\",\":six:\":\"36-20e3\",\":six_pointed_star:\":\"1f52f\",\":small_blue_diamond:\":\"1f539\",\":small_orange_diamond:\":\"1f538\",\":small_red_triangle:\":\"1f53a\",\":small_red_triangle_down:\":\"1f53b\",\":soon:\":\"1f51c\",\":sos:\":\"1f198\",\":symbols:\":\"1f523\",\":taurus:\":\"2649\",\":three:\":\"33-20e3\",\":tm:\":\"2122\",\":top:\":\"1f51d\",\":trident:\":\"1f531\",\":twisted_rightwards_arrows:\":\"1f500\",\":two:\":\"32-20e3\",\":u5272:\":\"1f239\",\":u5408:\":\"1f234\",\":u55b6:\":\"1f23a\",\":u6307:\":\"1f22f\",\":u6708:\":\"1f237\",\":u6709:\":\"1f236\",\":u6e80:\":\"1f235\",\":u7121:\":\"1f21a\",\":u7533:\":\"1f238\",\":u7981:\":\"1f232\",\":u7a7a:\":\"1f233\",\":underage:\":\"1f51e\",\":up:\":\"1f199\",\":vibration_mode:\":\"1f4f3\",\":virgo:\":\"264d\",\":vs:\":\"1f19a\",\":wavy_dash:\":\"3030\",\":wc:\":\"1f6be\",\":wheelchair:\":\"267f\",\":white_check_mark:\":\"2705\",\":white_circle:\":\"26aa\",\":white_flower:\":\"1f4ae\",\":white_square_button:\":\"1f533\",\":womens:\":\"1f6ba\",\":x:\":\"274c\",\":zero:\":\"30-20e3\"}};\n" +
            "</script>\n" +
            "<!-- Segment.io -->\n" +
            "<script>\n" +
            "  window.analytics=window.analytics||[],window.analytics.methods=[\"identify\",\"group\",\"track\",\"page\",\"pageview\",\"alias\",\"ready\",\"on\",\"once\",\"off\",\"trackLink\",\"trackForm\",\"trackClick\",\"trackSubmit\"],window.analytics.factory=function(t){return function(){var a=Array.prototype.slice.call(arguments);return a.unshift(t),window.analytics.push(a),window.analytics}};for(var i=0;i<window.analytics.methods.length;i++){var key=window.analytics.methods[i];window.analytics[key]=window.analytics.factory(key)}window.analytics.load=function(t){if(!document.getElementById(\"analytics-js\")){var a=document.createElement(\"script\");a.type=\"text/javascript\",a.id=\"analytics-js\",a.async=!0,a.src=(\"https:\"===document.location.protocol?\"https://\":\"http://\")+\"cdn.segment.io/analytics.js/v1/\"+t+\"/analytics.min.js\";var n=document.getElementsByTagName(\"script\")[0];n.parentNode.insertBefore(a,n)}},window.analytics.SNIPPET_VERSION=\"2.0.9\",\n" +
            "  window.analytics.load(\"wsxq90kyox\");\n" +
            "  window.analytics.page();\n" +
            "</script>\n" +
            "<script src=\"/assets/frontend/vendor-453c249d65fe9677b6cba9095168c970.js\"></script>\n" +
            "<script src=\"/assets/frontend/frontend-3040846076b33d5b7906305536e26732.js\"></script>\n" +
            "</body>\n" +
            "</html>\n";


    @Test
    public void testGetCsrfToken() throws Exception {
        assertTrue(CSRF_TOKEN.equals(JsoupUtils.getCsrfToken(SIGN_IN_PAGE)));
    }

}
