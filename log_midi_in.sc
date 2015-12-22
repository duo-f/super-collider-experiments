// probando MIDI con el NanoKontrol2
// buen tutorial https://www.youtube.com/watch?v=Oz4KYZ9KLc0

s.boot;

(
var on, bend, cc;
MIDIClient.init;
MIDIIn.connectAll;

// buttons
on = MIDIFunc.noteOn({ |val, num, chan, src|
	"noteOn".postln;
	[val, num, chan, src].postln;
});

// los sliders => van bien, desde 0 a 16383
bend = MIDIFunc.bend({ |val, num, chan, src|
	"bend".postln;
	[val, num, chan, src].postln;
});

// knobs => no andan bien. tienen 3 values: 1, 63, 65 ¿?
cc = MIDIFunc.cc({ |val, num, chan, src|
	"cc".postln;
	[val, num, chan, src].postln;
});

MIDIFunc.trace(true);
)


// Otra forma: con MIDIdef

(
MIDIdef.noteOn(\noteOnTest, {
	arg val, note, chan, src;
	[val, note, chan, src].postln;
});

MIDIdef.cc(\ccTest, {
	arg val, note, chan, src;
	[val, note, chan, src].postln;
});

MIDIdef.bend(\bendTest, {
	arg val, chan, src;
	[val, chan, src].postln;
});
)