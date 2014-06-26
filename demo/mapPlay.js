/////////method after load/////////////

function panZoom_CDH() {
	var dest = POS.CHONG_DE_HU;
	map.panTo(dest);
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	zoomTo(12, 1000, dest, function() {
		mk.setAnimation(null);
		show_cdh();
	});
}
function show_cdh() {
	var dest = POS.CHONG_DE_HU;
	showInfoWin(dest, $('#image_pair').show().get(0), 3000);
	setTimeout(function(){
		showInfoWin(dest, $('#chongdehu2').show().get(0), 3000);
	},3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_WJ();
		});
	}, 6000);
}

function panZoom_WJ() {
	var dest = POS.WEN_JIANG;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.CHONG_DE_HU, dest, 2, function() {
			zoomTo(12, 1000, dest, function() {
				mk.setAnimation(null);
				show_wj();
			});
		});
	}, 1000);
}
function show_wj() {
	var dest = POS.WEN_JIANG;
	showInfoWin(dest, $('#wenjiang').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_SNZH();
		});
	}, 3000);
}

function panZoom_SNZH() {
	var dest = POS.SHUNAN_ZHUHAI;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.WEN_JIANG, dest, 1, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_snzh();
			});
		});
	}, 1000);
}
function show_snzh() {
	var dest = POS.SHUNAN_ZHUHAI;
	showInfoWin(dest, $('#shunan_zhuhai').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_EMS();
		});
	}, 3000);
}

function panZoom_EMS() {
	var dest = POS.E_MEI_SHAN;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.SHUNAN_ZHUHAI, dest, 1, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_ems();
			});
		});
	}, 1000);
}
function show_ems() {
	var dest = POS.E_MEI_SHAN;
	showInfoWin(dest, $('#emeishan').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_LF();
		});
	}, 3000);
}

function panZoom_LF() {
	var dest = POS.LAI_FENG;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.E_MEI_SHAN, dest, 3, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_lf();
			});
		});
	}, 1000);
}
function show_lf() {
	var dest = POS.LAI_FENG;
	showInfoWin(dest, $('#laifeng').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(9, 1000, dest, function() {
			panZoom_QJ();
		});
	}, 3000);
}

function panZoom_QJ() {
	var dest = POS.QIAN_JIANG;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.LAI_FENG, dest, 1, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_qj();
			});
		});
	}, 1000);
}
function show_qj() {
	var dest = POS.QIAN_JIANG;
	showInfoWin(dest, $('#qianjiang').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_XC();
		});
	}, 3000);
}

function panZoom_XC() {
	var dest = POS.XI_CHANG;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.QIAN_JIANG, dest, 3, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_xc();
			});
		});
	}, 1000);
}
function show_xc() {
	var dest = POS.XI_CHANG;
	showInfoWin(dest, $('#xichang').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(8, 1000, dest, function() {
			panZoom_DJY();
		});
	}, 3000);
}

function panZoom_DJY() {
	var dest = POS.DU_JIANG_YAN;
	var mk = showMarker(dest, null, qq.maps.MarkerAnimation.BOUNCE);
	setTimeout(function(){
		panTo(POS.XI_CHANG, dest, 2, function() {
			zoomTo(11, 1000, dest, function() {
				mk.setAnimation(null);
				show_djy();
			});
		});
	}, 1000);
}
function show_djy() {
	var dest = POS.DU_JIANG_YAN;
	showInfoWin(dest, $('#dujiangyan').show().get(0), 3000);
	setTimeout(function(){
		zoomOutTo(10, 1000, dest, function() {
			// panZoom_DJY();
			cd_lx();
		});
	}, 3000);
}
function cd_lx() {
	map.panTo(new qq.maps.LatLng(30.276447,104.468517));
	map.zoomTo(map.getZoom()-1);
	var mk = showMarker(POS.CHENG_DU, null, qq.maps.MarkerAnimation.BOUNCE);
	var mk2 = showMarker(POS.LU_XIAN, null, qq.maps.MarkerAnimation.BOUNCE);

	showInfoWin(POS.CHENG_DU, $('#chengdu').show().get(0), 3000);
	setTimeout(function(){
		showInfoWin(POS.LU_XIAN, $('#luxian').show().get(0), 3000);	
		mk.setAnimation(null);
		mk2.setAnimation(null);
		calcRoute(POS.CHENG_DU, POS.LU_XIAN);
	},3000);
}

function paint() {
map.setMapTypeId(qq.maps.MapTypeId.SATELLITE);
map.panTo(new qq.maps.LatLng(	39.968734,81.690117	));
var micon = new qq.maps.MarkerImage("icon/stone-24.png");
// sheng |
showMarker(	new qq.maps.LatLng(	39.608734,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	39.458734,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	39.308279,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	39.168279,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	39.038279,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	38.888279,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	38.718279,80.190117	), micon);
showMarker(	new qq.maps.LatLng(	38.568279,80.190117	), micon);
// )
showMarker(	new qq.maps.LatLng(	39.358279,79.700117	), micon);
showMarker(	new qq.maps.LatLng(	39.008279,79.420117	), micon);
// -
showMarker(	new qq.maps.LatLng(	39.168279,79.640117	), micon);
showMarker(	new qq.maps.LatLng(	39.168279,79.820117	), micon);
showMarker(	new qq.maps.LatLng(	39.168279,80.000117	), micon);
showMarker(	new qq.maps.LatLng(	39.168279,80.380117	), micon);
showMarker(	new qq.maps.LatLng(	39.178279,80.550117	), micon);
showMarker(	new qq.maps.LatLng(	39.188279,80.750117	), micon);
// -
showMarker(	new qq.maps.LatLng(	38.868279,79.720117	), micon);
showMarker(	new qq.maps.LatLng(	38.868279,79.950117	), micon);
showMarker(	new qq.maps.LatLng(	38.898279,80.390117	), micon);
showMarker(	new qq.maps.LatLng(	38.918279,80.620117	), micon);
// -
showMarker(	new qq.maps.LatLng(	38.508279,79.420117	), micon);
showMarker(	new qq.maps.LatLng(	38.528279,79.600117	), micon);
showMarker(	new qq.maps.LatLng(	38.548279,79.820117	), micon);
showMarker(	new qq.maps.LatLng(	38.558279,80.020117	), micon);
showMarker(	new qq.maps.LatLng(	38.568279,80.350117	), micon);
showMarker(	new qq.maps.LatLng(	38.568279,80.520117	), micon);
showMarker(	new qq.maps.LatLng(	38.568279,80.720117	), micon);
showMarker(	new qq.maps.LatLng(	38.558279,80.900117	), micon);

// ri -
showMarker(	new qq.maps.LatLng(	39.508734,81.750116	), micon);
showMarker(	new qq.maps.LatLng(	39.538734,82.050117	), micon);
showMarker(	new qq.maps.LatLng( 39.558734,82.320117 ), micon);
showMarker(	new qq.maps.LatLng(	39.573353,82.574834	), micon);
// |
showMarker(	new qq.maps.LatLng(	39.308734,81.760117	), micon);
showMarker(	new qq.maps.LatLng(	39.108734,81.770117	), micon);
showMarker(	new qq.maps.LatLng(	38.858734,81.770117	), micon);
showMarker(	new qq.maps.LatLng(	38.608734,81.770117	), micon);
// |
showMarker(	new qq.maps.LatLng(	39.308734,82.574834	), micon);
showMarker(	new qq.maps.LatLng(	39.108734,82.584834	), micon);
showMarker(	new qq.maps.LatLng(	38.858734,82.584834	), micon);
showMarker(	new qq.maps.LatLng(	38.558734,82.594833	), micon);
// -
showMarker(	new qq.maps.LatLng(	39.118734,82.050117	), micon);
showMarker(	new qq.maps.LatLng(	39.138734,82.330117	), micon);
// -
showMarker(	new qq.maps.LatLng(	38.618734,82.050117	), micon);
showMarker(	new qq.maps.LatLng(	38.628734,82.320117	), micon);

// kuai |
showMarker(	new qq.maps.LatLng(	40.208734,83.704834	), micon);
showMarker(	new qq.maps.LatLng(	40.008734,83.714834	), micon);
showMarker(	new qq.maps.LatLng(	39.808734,83.724834	), micon);
showMarker(	new qq.maps.LatLng(	39.608734,83.724834	), micon);
showMarker(	new qq.maps.LatLng(	39.408734,83.734834	), micon);
showMarker(	new qq.maps.LatLng(	39.158734,83.744834	), micon);
showMarker(	new qq.maps.LatLng(	38.858734,83.754834	), micon);
// '
showMarker(	new qq.maps.LatLng(	39.708734,83.424834	), micon);
showMarker(	new qq.maps.LatLng(	39.508734,83.544834	), micon);
// '
showMarker(	new qq.maps.LatLng(	39.758734,83.924834	), micon);
// ¿ì ]
showMarker(	new qq.maps.LatLng(	39.808734,84.004834	), micon);
showMarker(	new qq.maps.LatLng(	39.828734,84.204834	), micon);
showMarker(	new qq.maps.LatLng(	39.838734,84.404834	), micon);
showMarker(	new qq.maps.LatLng(	39.848734,84.654834	), micon);
showMarker(	new qq.maps.LatLng(	39.858734,84.984834	), micon);
//
showMarker(	new qq.maps.LatLng(	39.688734,84.904834	), micon);
showMarker(	new qq.maps.LatLng(	39.528734,84.824834	), micon);
//
showMarker(	new qq.maps.LatLng(	39.428734,84.004834	), micon);
showMarker(	new qq.maps.LatLng(	39.458734,84.224834	), micon);
showMarker(	new qq.maps.LatLng(	39.487340,84.424834	), micon);
showMarker(	new qq.maps.LatLng(	39.508734,84.624834	), micon);
showMarker(	new qq.maps.LatLng(	39.528734,84.824834	), micon);
showMarker(	new qq.maps.LatLng(	39.528734,85.084834	), micon);
showMarker(	new qq.maps.LatLng(	39.538734,85.324834	), micon);
//
showMarker(	new qq.maps.LatLng(	39.658734,84.424834	), micon);
showMarker(	new qq.maps.LatLng(	39.287340,84.354834	), micon);
showMarker(	new qq.maps.LatLng(	39.107340,84.254834	), micon);
showMarker(	new qq.maps.LatLng(	38.957340,84.004834	), micon);
//
showMarker(	new qq.maps.LatLng(	40.108734,84.404834	), micon);
// (
showMarker(	new qq.maps.LatLng(	39.287340,84.604834	), micon);
showMarker(	new qq.maps.LatLng(	39.107340,84.854834	), micon);
showMarker(	new qq.maps.LatLng(	38.907340,85.024834	), micon);

// ÀÖ |
showMarker(	new qq.maps.LatLng(	40.258734,86.814833	), micon);
showMarker(	new qq.maps.LatLng(	40.068734,86.824834	), micon);
showMarker(	new qq.maps.LatLng(	39.908733,86.824834	), micon);
showMarker(	new qq.maps.LatLng(	39.708734,86.834834	), micon);
showMarker(	new qq.maps.LatLng(	39.458734,86.844833	), micon);
showMarker(	new qq.maps.LatLng(	39.158733,86.854834	), micon);
// -
showMarker(	new qq.maps.LatLng(	39.848734,86.224834	), micon);
showMarker(	new qq.maps.LatLng(	39.868733,86.524834	), micon);
showMarker(	new qq.maps.LatLng(	39.918734,87.124833	), micon);
showMarker(	new qq.maps.LatLng(	39.928734,87.484833	), micon);
// )
showMarker(	new qq.maps.LatLng(	40.378733,87.114834	), micon);
showMarker(	new qq.maps.LatLng(	40.158733,86.554834	), micon);
showMarker(	new qq.maps.LatLng(	40.108734,86.204833	), micon);
// )
showMarker(	new qq.maps.LatLng(	39.758734,86.634834	), micon);
showMarker(	new qq.maps.LatLng(	39.558733,86.504834	), micon);
showMarker(	new qq.maps.LatLng(	39.358734,86.304834	), micon);
showMarker(	new qq.maps.LatLng(	39.158733,86.134834	), micon);
// (
showMarker(	new qq.maps.LatLng(	39.708734,87.034834	), micon);
showMarker(	new qq.maps.LatLng(	39.558733,87.154834	), micon);
showMarker(	new qq.maps.LatLng(	39.408733,87.384834	), micon);
showMarker(	new qq.maps.LatLng(	39.308733,87.634834	), micon);


}
function getpoint() {
var micon = new qq.maps.MarkerImage("icon/Citrine-24.png");
map.setMapTypeId(qq.maps.MapTypeId.SATELLITE);
map.panTo(new qq.maps.LatLng(	39.968734,81.690117	));
	qq.maps.event.addListener(map,"click",function(e){
        console.log(e.latLng.getLat().toFixed(4) + '-' + e.latLng.getLng().toFixed(4));
    });
var le = [

{lat:	40.3047, lng:86.8744	},
{lat:	40.2879, lng:86.7151	},
{lat:	40.2711, lng:86.5503	},
{lat:	40.2544, lng:86.4349	},
{lat:	40.2544, lng:86.3031	},
{lat:	40.2460, lng:86.1823	},
{lat:	40.1075, lng:86.1713	},
{lat:	39.9434, lng:86.1383	},
{lat:	39.9434, lng:86.2701	},
{lat:	39.9476, lng:86.4240	},
{lat:	39.9519, lng:86.5723	},
{lat:	39.9392, lng:86.7316	},
{lat:	39.9392, lng:86.8744	},
{lat:	39.9434, lng:87.0227	},
{lat:	40.1705, lng:86.5668	},
{lat:	40.0528, lng:86.5668	},
{lat:	39.9519, lng:86.5723	},
{lat:	39.8339, lng:86.5778	},
{lat:	39.7199, lng:86.5668	},
{lat:	39.6226, lng:86.5723	},
{lat:	39.5083, lng:86.5228	},
{lat:	39.5549, lng:86.3800	},
{lat:	39.8296, lng:86.3416	},
{lat:	39.7241, lng:86.2427	},
{lat:	39.6607, lng:86.1383	},
{lat:	39.5676, lng:85.9900	},
{lat:	39.8423, lng:86.7371	},
{lat:	39.7241, lng:86.8964	},
{lat:	39.5845, lng:86.9733	}


];
/*
for (var i = 0; i< le.length; i++) {
	showMarker(	new qq.maps.LatLng(	le[i].lat,le[i].lng	), micon);
}
*/
}

function main() {
	init_map();
	//panZoom_CDH();
	//panZoom_WJ();
	//panZoom_SNZH();
	//panZoom_LF();
	//panZoom_QJ();
	//panZoom_XC();
	//panZoom_DJY();
	//paint();
	getpoint();
	
}