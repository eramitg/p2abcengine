//* Licensed Materials - Property of                                  *
//* IBM                                                               *
//* Alexandra Instituttet A/S                                         *
//*                                                                   *
//* eu.abc4trust.pabce.1.34                                           *
//*                                                                   *
//* (C) Copyright IBM Corp. 2014. All Rights Reserved.                *
//* (C) Copyright Alexandra Instituttet A/S, Denmark. 2014. All       *
//* Rights Reserved.                                                  *
//* US Government Users Restricted Rights - Use, duplication or       *
//* disclosure restricted by GSA ADP Schedule Contract with IBM Corp. *
//*                                                                   *
//* This file is licensed under the Apache License, Version 2.0 (the  *
//* "License"); you may not use this file except in compliance with   *
//* the License. You may obtain a copy of the License at:             *
//*   http://www.apache.org/licenses/LICENSE-2.0                      *
//* Unless required by applicable law or agreed to in writing,        *
//* software distributed under the License is distributed on an       *
//* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY            *
//* KIND, either express or implied.  See the License for the         *
//* specific language governing permissions and limitations           *
//* under the License.                                                *
//*/**/****************************************************************

package eu.abc4trust.abce.external.user;

import java.net.URI;
import java.util.List;

import com.google.inject.Inject;

import eu.abc4trust.abce.internal.user.credentialManager.CredentialManagerException;
import eu.abc4trust.cryptoEngine.CryptoEngineException;
import eu.abc4trust.exceptions.CannotSatisfyPolicyException;
import eu.abc4trust.keyManager.KeyManagerException;
import eu.abc4trust.returnTypes.IssuMsgOrCredDesc;
import eu.abc4trust.returnTypes.IssuanceReturn;
import eu.abc4trust.returnTypes.UiIssuanceReturn;
import eu.abc4trust.returnTypes.UiPresentationArguments;
import eu.abc4trust.returnTypes.UiPresentationReturn;
import eu.abc4trust.xml.CredentialDescription;
import eu.abc4trust.xml.IssuanceMessage;
import eu.abc4trust.xml.IssuancePolicy;
import eu.abc4trust.xml.PresentationPolicyAlternatives;
import eu.abc4trust.xml.PresentationToken;

public class SynchronizedUserAbcEngineImpl implements UserAbcEngine {

    private final UserAbcEngine engine;

    @Inject
    public SynchronizedUserAbcEngineImpl(UserAbcEngine engine) {
        this.engine = engine;
    }

    @Override
    public synchronized boolean canBeSatisfied(String username, PresentationPolicyAlternatives p)
            throws CredentialManagerException, CryptoEngineException {
        return this.engine.canBeSatisfied(username, p);
    }



    @Override
    public synchronized void updateNonRevocationEvidence(String username)
            throws CredentialManagerException {
        this.engine.updateNonRevocationEvidence(username);
    }

    @Override
    public synchronized List<URI> listCredentials(String username)
            throws CredentialManagerException {
        return this.engine.listCredentials(username);
    }

    @Override
    public synchronized CredentialDescription getCredentialDescription(String username, 
            URI credUid)
                    throws CredentialManagerException {
        return this.engine.getCredentialDescription(username, credUid);
    }

    @Override
    public synchronized boolean deleteCredential(String username, URI credUid)
            throws CredentialManagerException {
        return this.engine.deleteCredential(username, credUid);
    }

    @Override
    public synchronized boolean isRevoked(String username, URI credUid)
            throws CryptoEngineException {
        return this.engine.isRevoked(username, credUid);
    }

    @Override
    public synchronized UiPresentationArguments createPresentationToken(String username, 
            PresentationPolicyAlternatives p) throws CannotSatisfyPolicyException,
            CredentialManagerException, KeyManagerException, CryptoEngineException {
        return this.engine.createPresentationToken(username, p);
    }

    @Override
    public synchronized PresentationToken createPresentationToken(String username, UiPresentationReturn upr)
            throws CredentialManagerException, CryptoEngineException {
        return this.engine.createPresentationToken(username, upr);
    }

    @Override
    public synchronized IssuanceReturn issuanceProtocolStep(String username, IssuanceMessage im)
            throws CannotSatisfyPolicyException, CryptoEngineException, CredentialManagerException, KeyManagerException {
        return this.engine.issuanceProtocolStep(username, im);
    }

    @Override
    public synchronized IssuanceMessage issuanceProtocolStep(String username, UiIssuanceReturn uir)
            throws CryptoEngineException {
        return this.engine.issuanceProtocolStep(username, uir);
    }

    @Override
    public synchronized PresentationToken createPresentationTokenFirstChoice(String username,
        PresentationPolicyAlternatives p) throws CannotSatisfyPolicyException,
        CredentialManagerException, KeyManagerException, CryptoEngineException {
      return this.engine.createPresentationTokenFirstChoice(username, p);
    }

    @Override
    public synchronized IssuMsgOrCredDesc issuanceProtocolStepFirstChoice(String username, IssuanceMessage im)
        throws CannotSatisfyPolicyException, CryptoEngineException, CredentialManagerException,
        KeyManagerException {
      return this.engine.issuanceProtocolStepFirstChoice(username, im);
    }

	@Override
	public List<String> createHumanReadablePresentationPolicy(
			PresentationPolicyAlternatives ppa) throws KeyManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> createHumanReadableIssuancePolicy(IssuancePolicy ip)
			throws KeyManagerException {
		// TODO Auto-generated method stub
		return null;
	}

}
