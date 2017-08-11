package io.legaldocml.io.impl;

import io.legaldocml.io.CharBuffer;
import io.legaldocml.io.ProcessingInstruction;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class ProcessingInstructionImpl implements ProcessingInstruction {

    private final char[] val;

    private String target;
    private String instruction;

    ProcessingInstructionImpl(CharBuffer cb) {
        this.val = cb.value();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTarget() {
        if (target == null) {
            init();
        }
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInstruction() {
        if (target == null) {
            init();
        }
        return instruction;
    }

    private void init() {
        char[] val = this.val;
        int i = 0;
        for (int n = val.length - 2; i < n; i++) {
            if (val[i] == ' ') {
                break;
            }
        }
        this.target = new String(val, 0, i );
        if (i != val.length - 2) {
            this.instruction = new String(val, i + 1, val.length - 3 - i);
        }
    }
}
