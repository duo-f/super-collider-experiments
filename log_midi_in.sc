// probando MIDI con el NanoKontrol2

s.boot;

(
var on, bend, cc;
MIDIClient.init;
MIDIIn.connectAll;

// buttons
on = MIDIFunc.noteOn({ |veloc, num, chan, src|
	"noteOn".postln;
	[veloc, num, chan, src].postln;
});

// los sliders => van bien, desde 0 a 16383
bend = MIDIFunc.bend({ |veloc, num, chan, src|
	"bend".postln;
	[veloc, num, chan, src].postln;
});

// knobs => no andan bien. tienen 3 values: 1, 63, 65 ¿?
cc = MIDIFunc.cc({ |veloc, num, chan, src|
	"cc".postln;
	[veloc, num, chan, src].postln;
});

MIDIFunc.trace(true);
)