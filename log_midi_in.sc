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

// ------------------------------------------------------------------- //
// Otra forma: con MIDIdef

s.boot
MIDIClient.init;
MIDIIn.connectAll;

(
MIDIdef.noteOn(\noteOnTest, {
	arg val, note, chan, src;
	[val, note, chan, src].postln;

	{
		var sig, env;
		sig = SinOsc.ar(note.midicps)!2; // !2 para que salga stereo
		env = EnvGen.kr(Env.perc, doneAction: 2);
		sig = sig  * val.linexp(1, 127, 0.01, 0.3);
	}.play;
});

MIDIdef.cc(\ccTest, {
	arg val, note, chan, src;
	[val, note, chan, src].postln;
});

MIDIdef.bend(\bendTest, {
	arg val, chan, src;
	[val, chan, src].postln;

	{
		var sig, env;
		sig = SinOsc.ar(freq: 2000 * val.linexp(1, 16383, 0.01, 0.3))!2; // para que salga stereo
		env = EnvGen.kr(Env.perc, doneAction: 2);
		sig = sig * env * val.linexp(1, 16383, 0.01, 0.3);
	}.play;
});
)

s.quit